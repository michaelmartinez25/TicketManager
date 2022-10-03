package edu.bowdoin.csci.TicketManager.model.io;

import java.util.List;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Writes ticket files
 * 
 * @author mmartinez
 */
public class TicketWriter {
	
	/** Constructor method */
	public TicketWriter() {
	
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
		
	}
}
