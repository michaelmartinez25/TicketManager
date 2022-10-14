package edu.bowdoin.csci.TicketManager.model.manager;

import java.util.List;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Maintains a list of tickets.
 * 
 * @author mmartinez
 */
public class TicketList {

	/**
	 * Constructor method
	 */
	public TicketList() {
		//comment for PMD
	}
	
	/**
	 * Adds a ticket to the list
	 * 
	 * @param type type of the ticket to be added
	 * @param subject subject of the ticket to be added
	 * @param caller caller of the ticket to be added
	 * @param category category of the ticket to be added
	 * @param priority priority of the ticket to be added
	 * @param note note of the ticket to be added
	 * @return index of the newly added ticket
	 */
	public int addTicket(TicketType type, String subject, String caller, Category category, Priority priority, String note) {
		return 0;
	}
	
	/**
	 * Adds a list of tickets to the current list
	 * 
	 * @param ticketList the list of tickets to be added
	 */
	public void addTickets(List<Ticket> ticketList) {
		//comment for PMD
	}
	
	/**
	 * Gets the entire list of tickets
	 * 
	 * @return the entire list
	 */
	public List<Ticket> getTickets() {
		return null;
	}
	
	/**
	 * Gets a filtered list based on the given type
	 * 
	 * @param type type to filter the list by
	 * @return the filtered list of the desired type
	 */
	public List<Ticket> getTicketsByType(TicketType type) {
		return null;
	}
	
	/**
	 * Gets a single ticket based on the given ticketId
	 * 
	 * @param ticketId id of desired ticket in list
	 * @return the desired ticket of ticketId
	 */
	public Ticket getTicketById(int ticketId) {
		return null;
	}
	
	/**
	 * Executes a given command on a ticket associated
	 * with the given ticketId.
	 * 
	 * Note: Implementation notes do not specify 'int' parameter,
	 * so Michael assumed it's a ticketId.
	 * 
	 * @param ticketId id of desired ticket
	 * @param command command to be executed
	 */
	public void executeCommand(int ticketId, Command command) {
		//comment for PMD
	}
	
	/**
	 * Deletes a ticket from the list based on the given id
	 * 
	 * @param ticketId id of desired ticket
	 */
	public void deleteTicketById(int ticketId) {
		//comment for PMD
	}
}
