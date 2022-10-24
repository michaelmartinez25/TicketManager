package edu.bowdoin.csci.TicketManager.model.manager;

import java.io.IOException;
import java.util.List;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.io.TicketReader;
import edu.bowdoin.csci.TicketManager.model.io.TicketWriter;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Concrete class that maintains the 'TicketList' and handles 'Commands' from the GUI.
 * Implements the Singleton Design Pattern.
 * Controls the creation and modification of TicketLists
 * @author mmartinez
 */
public class TicketManager {
	
	/** Stores the one and only instance of this class*/
	static private TicketManager instance = new TicketManager();
	/** The active ticketList */
	private TicketList ticketList;
	
	/**
	 * Constructor for TicketManager.
	 * Note: There can only be one instance of this object as part
	 * of the Singleton Design Pattern.
	 */
	private TicketManager() {
		ticketList = new TicketList();
	}
	
	/** 
	 * Gets the single instance of the TicketManager
	 * 
	 * @return TicketManager instance
	 */
	public static TicketManager getInstance() {
		return instance;
	}
	
	/** 
	 * Saves an active ticket list into a given file
	 * 
	 * @param filename file user saves ticket list to
	 * @throws IllegalArgumentException if it catches an IOException
	 */
	public void saveTicketsToFile(String filename) {
		try {
			TicketWriter.writeTicketFile(filename, ticketList.getTickets());
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException();
		}
	}
	
	/** 
	 * Loads a ticket list from a given file
	 * 
	 * @param filename file user loads ticket list from
	 * @throws IllegalException if it catches an IOException
	 */
	public void loadTicketsFromFile(String filename) {
		List<Ticket> tickets;
		try {
			tickets = TicketReader.readTicketFile(filename);
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException();
		}
		
		ticketList.addTickets(tickets);
	}
	
	/** 
	 * Updates the global TicketList reference to point to a new,
	 * empty TicketList object. The old TicketList will be garbage
	 * collected. We will assume that the user will have saved the 
	 * old TicketList (if wanted) before calling this operation.
	 */
	public void createNewTicketList() {
		ticketList = new TicketList();
	}
	
	/** 
	 * Builds a 2D String array to display tickets in rows and 
	 * each ticket's information in columns. Array should have
	 * 1 row for every ticket and 5 columns.
	 * 
	 * @return a String representation of ticket list
	 */
	public String[][] getTicketsForDisplay() {
		List<Ticket> tickets = ticketList.getTickets();
		String[][] ticketDisplay = new String[tickets.size()][6];
		for (int i = 0; i < tickets.size(); i++) {
			Ticket currTicket = tickets.get(i);
			Integer ticketId = currTicket.getTicketId();
			ticketDisplay[i][0] = ticketId.toString();
			ticketDisplay[i][1] = currTicket.getTicketTypeString();
			ticketDisplay[i][2] = currTicket.getState();
			ticketDisplay[i][3] = currTicket.getSubject();
			ticketDisplay[i][4] = currTicket.getCategory();
			ticketDisplay[i][5] = currTicket.getPriority();
		}
		
		return ticketDisplay;
	}
	
	/**
	 * Builds a 2D String array to display tickets of a given 
	 * type in rows and each ticket's information in columns. 
	 * 
	 * @param type Desired type of tickets to display
	 * @return a String representation of a filtered ticket list
	 * @throws IllegalArgumentException if TicketType is null
	 */
	public String[][] getTicketsForDisplayByType(TicketType type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		
		List<Ticket> filteredTickets = ticketList.getTicketsByType(type);
		String[][] ticketDisplay = new String[filteredTickets.size()][6];
		for (int i = 0; i < filteredTickets.size(); i++) {
			Ticket currTicket = filteredTickets.get(i);
			Integer ticketId = currTicket.getTicketId();
			ticketDisplay[i][0] = ticketId.toString();
			ticketDisplay[i][1] = currTicket.getTicketTypeString();
			ticketDisplay[i][2] = currTicket.getState();
			ticketDisplay[i][3] = currTicket.getSubject();
			ticketDisplay[i][4] = currTicket.getCategory();
			ticketDisplay[i][5] = currTicket.getPriority();
		}
		
		return ticketDisplay;
	}
	
	/**
	 * Gets a ticket from list associated with a given ticketId
	 * 
	 * @param ticketId id of desired ticket
	 * @return the ticket associated with the ticketId
	 */
	public Ticket getTicketById(int ticketId) {
		return ticketList.getTicketById(ticketId);
	}
	
	/**
	 * Executes a given command on a ticket associated
	 * with the given ticketId.
	 * 
	 * @param ticketId id of desired ticket
	 * @param command command to be executed
	 */
	public void executeCommand(int ticketId, Command command) {
		try {
			ticketList.executeCommand(ticketId, command);
		} catch (UnsupportedOperationException uoe) {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Deletes a ticket from list associated with a given ticketId.
	 * 
	 * @param ticketId id of desired ticket
	 */
	public void deleteTicketById(int ticketId) {
		ticketList.deleteTicketById(ticketId);
	}
	
	/**
	 * Adds a ticket to TicketList
	 * 
	 * @param type type of ticket to be added
	 * @param subject subject of ticket to be added
	 * @param caller caller of ticket to be added
	 * @param category category of ticket to be added
	 * @param priority priority of ticket to be added
	 * @param note note of ticket to be added
	 */
	public void addTicketToList(TicketType type, String subject, String caller, Category category, Priority priority, String note) {
		ticketList.addTicket(type, subject, caller, category, priority, note);
	}
}
