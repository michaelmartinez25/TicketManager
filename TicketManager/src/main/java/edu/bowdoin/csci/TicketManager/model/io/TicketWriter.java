package edu.bowdoin.csci.TicketManager.model.io;

import java.util.List;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
/**
 * Writes ticket files
 * 
 * @author mmartinez
 */
public class TicketWriter {
	
	/** Constructor method */
	public TicketWriter() {
		//comment for PMD
	}
	
	/**
	 * Receives a String with the file name to write to and a List of Tickets
	 * to write. Uses Ticket's toString() method to create the properly formatted
	 * output for a Ticket. If there are any errors, an IllegalArgumentException
	 * is thrown with the message "Unable to save file"
	 * 
	 * @param filename file to write to 
	 * @param ticketList list of Tickets to write
	 * @throws IllegalArgumentException if unable to save file
	 */
	public static void writeTicketFile(String filename, List<Ticket> ticketList) {
		//comment for PMD
		IllegalArgumentException iae = new IllegalArgumentException("Unable to save file."); 
		
		if (filename == null || ticketList == null) {
			throw iae; 
		}
		
		String output = ""; 
		
		for (Ticket t: ticketList) {
			output += TicketWriter.writeTicket(t);
		}
		
		try {
			//append = false. Overwrite file. 
			FileWriter writer = new FileWriter(filename, false); 
			writer.write(output); 
			writer.close(); 
		}
		catch (Exception e) {
			throw iae; 
		}
		
		
	}
	
	/**
	 * Helper method to create string representation of ticket with correct delimiters for storage
	 * @param t ticket 
	 * @return string representing ticket to write to file
	 */
	private static String writeTicket(Ticket t) {
		
		String output = "*"; 
		
		output += t.getTicketId();
		output += "#" + t.getState(); 
		output += "#" + t.getTicketTypeString(); 
		output += "#" + t.getSubject(); 
		output += "#" + t.getCaller(); 
		output += "#" + t.getCategory();
		output += "#" + t.getPriority(); 
		output += "#" + t.getOwner(); 
		//assumes that unused codes are stored as null
		output += "#"; 
		if (t.getCancellationCode() != null) output += t.getCancellationCode();
		if (t.getFeedbackCode() != null) output += t.getFeedbackCode(); 
		if (t.getResolutionCode() != null) output += t.getResolutionCode(); 
		output += "\n"; 
		
		output += t.getNotes(); 
		
		return output; 
	}
}
