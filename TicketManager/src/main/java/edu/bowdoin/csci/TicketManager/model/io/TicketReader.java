package edu.bowdoin.csci.TicketManager.model.io;

import java.util.ArrayList;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Reads ticket files
 * 
 * @author mmartinez
 */
public class TicketReader {

	/** Constructor method */
	public TicketReader() {
		
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
		return null;
	}
}
