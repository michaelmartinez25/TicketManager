package edu.bowdoin.csci.TicketManager.model.io;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Reads ticket files
 * 
 * @author zbecker2
 * @author mmartinez
 */
public class TicketReader {

	/** Constructor method */
	public TicketReader() {
		//comment for PMD
	}
	
	/**
	 * Receives a String with the file name to read from. If
	 * there are any errors when processing the file, it will throw
	 * an IllegalArgumentException with the message "Unable to load file"
	 * 
	 * @param filename file to read from
	 * @return a list of tickets from the file
	 * @throws IllegalArgumentException if unable to load file
	 */
	public static ArrayList<Ticket> readTicketFile(String filename) {
		
		ArrayList<Ticket> list = new ArrayList<Ticket>(); 
		IllegalArgumentException iae = new IllegalArgumentException("Unable to load file."); 
		
		
		try {
			File file = new File(filename); 
			Scanner scanner = new Scanner(file).useDelimiter("\n"); 
			
			while(scanner.hasNextLine()) {
				//read in line
				//assume we are at a new ticket here 
				String line = scanner.nextLine();
				
				if (line.charAt(0) != '*') {
					//We should only get to this point in the code while expecting a new ticket
					throw iae;
				}
				
				//substring excludes the initial '*'
				String[] input = line.substring(1).split("#"); 
				
				int id = Integer.parseInt(input[0]); 
				String state = input[1]; 
				String ticketType = input[2];
				String subject = input[3];
				String caller = input[4]; 
				String category = input[5]; 
				String priority = input[6];
				String owner = input[7]; 
				String code = input[8];
				ArrayList<String> notes = new ArrayList<String>(); 
				
				//while next line is a note: note = scanner.nextLine()
				// ^-.* means -> first character is '-', followed by any number of any character
				while (scanner.hasNext("^-.*")) {
					//consume notes
					String note = scanner.nextLine(); 
					notes.add(note); 
				}
				
				Ticket ticket = new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes);
				list.add(ticket);
			
				
			}
			
			scanner.close();
			return list; 
		}
		
		catch (Exception e) {
			throw iae; 
		}
	
	}
}
