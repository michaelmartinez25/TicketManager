package edu.bowdoin.csci.TicketManager.model.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * The TicketReader class provides static methods for reading tickets from a text file. 
 * 
 * @author Zeb Becker
 */
public class TicketReader {

	/** Constructor method */
	public TicketReader() {
		//comment for PMD
	}
	
	/**
	 * Receives a String with the file name to read from. If
	 * there are any errors when processing the file, it will throw
	 * an IllegalArgumentException with the message "Unable to load file."
	 * 
	 * @param filename file to read from
	 * @return a list of tickets successfully read from the file
	 * @throws IllegalArgumentException if unable to load file for any reason. 
	 */
	public static ArrayList<Ticket> readTicketFile(String filename) {
		
		ArrayList<Ticket> list = new ArrayList<Ticket>(); 
		IllegalArgumentException iae = new IllegalArgumentException("Unable to load file."); 
		Scanner scanner = null;
		
		
		try {
			File file = new File(filename); 
			scanner = new Scanner(file).useDelimiter("\n"); 
			
			ArrayList<String> currentNotes = null; 
			//prevTicketString and prevNote act as buffers, holding the last ticket and note we saw while we check
			//the next line to determine if that line needs to be included in one or both of them. 
			String prevTicketString = null; 
			String prevNote = null; 
			
			while (scanner.hasNextLine()) {
				
				String line = scanner.nextLine(); 
				
				if (line.charAt(0) == '*') {
					
					if (prevNote != null) {
						addNote(prevNote, currentNotes); 
						prevNote = null;
					}
					
					if (prevTicketString != null) {
						list.add(readTicketLine(prevTicketString, currentNotes)); 
						prevTicketString = null; 
						currentNotes = null; 
					}
					
					prevTicketString = line; 
					currentNotes = new ArrayList<String>(); 
				} else if (line.charAt(0) == '-') {
					
					if (prevNote != null) {
						addNote(prevNote, currentNotes); 
					}
					prevNote = line; 
					
				} else {
					
					if (prevNote == null) {
						throw iae; 
					}
					
					prevNote += "\n" + line; 
				}
			
			}
			
			//make sure we add last ticket 
			if (prevNote != null) {
				addNote(prevNote, currentNotes); 
			}
			if (prevTicketString != null && currentNotes != null) {
				list.add(readTicketLine(prevTicketString, currentNotes)); 
			}
	
			return list; 
		
		} catch (Exception e) {
			throw iae; 
		} finally {
			if (scanner != null) scanner.close(); 
		}
	}
	
	private static Ticket readTicketLine(String line, ArrayList<String> notes) {
		
		String[] input = line.substring(1).split("#"); 
		
		int id = Integer.parseInt(input[0]); 
		String state = input[1]; 
		String ticketType = input[2];
		String subject = input[3];
		String caller = input[4]; 
		String category = input[5]; 
		String priority = input[6];
		String owner = null; 
		String code = null; 
		
		if (input.length > 7) {
			owner = input[7]; 
		}
		if (input.length > 8) {
			code = input[8];
		}
		
		Ticket ticket = new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes);

		return ticket;
		
	}
	
	/**
	 * Helper method to ensure consistent internal representation of notes. Strips leading '-' for internal storage. 
	 * @param note to be added
	 * @param notes list of notes to add note to
	 */ 
	private static void addNote(String note, ArrayList<String> notes) {
		//strip leading '-', then add to list
		notes.add(note.substring(1));
	}
}
