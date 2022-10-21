package edu.bowdoin.csci.TicketManager.model.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	/** Backing ticketList holds tickets for each instance of class. */
	private ArrayList<Ticket> ticketList;
	
	/**
	 * Constructor method
	 */
	public TicketList() {
		ticketList = new ArrayList<Ticket>();
		Ticket.setCounter(1);
	}
	
	/**
	 * Adds a ticket to the list
	 * 
	 * @param type type of the ticket to be added, as TicketType
	 * @param subject subject of the ticket to be added, as String
	 * @param caller caller of the ticket to be added, as String
	 * @param category category of the ticket to be added, as Category
	 * @param priority priority of the ticket to be added, as Priority
	 * @param note note of the ticket to be added, as String
	 * @return index of the newly added ticket, as integer
	 */
	public int addTicket(TicketType type, String subject, String caller, Category category, Priority priority, String note) {
		Ticket newTicket = null;
		try {
			newTicket = new Ticket(type, subject, caller, category, priority, note);
		} catch(IllegalArgumentException iae) {
			throw new IllegalArgumentException();
		}
		ticketList.add(newTicket);
		return newTicket.getTicketId();
	}
	
	/**
	 * Adds a list of tickets to the current list. 
	 * 
	 * @param ticketList the list of tickets to be added
	 */
	public void addTickets(List<Ticket> ticketList) {
		if (ticketList == null || ticketList.size() == 0) {
			throw new IllegalArgumentException();
		}
		
		for (Ticket ticket: ticketList) {
			this.ticketList.add(ticket);
		}
	}
	
	/**
	 * Gets the entire list of tickets. 
	 * 
	 * @return the entire list
	 */
	public List<Ticket> getTickets() {
		return ticketList;
	}
	
	/**
	 * Gets a list of only tickets from this ticket list that match the given type. 
	 * 
	 * @param type type to filter the list by
	 * @return the filtered list of the desired type
	 */
	public List<Ticket> getTicketsByType(TicketType type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		
		List<Ticket> filteredList = new ArrayList<Ticket>();
		for (Ticket ticket: ticketList) {
			if (ticket.getTicketType().equals(type)) {
				filteredList.add(ticket);
			}
		}
		
		return filteredList;
	}
	
	/**
	 * Gets single ticket with ticket ID matching input ID. 
	 * 
	 * @param ticketId id of desired ticket in list
	 * @return the desired ticket of ticketId
	 */
	public Ticket getTicketById(int ticketId) {
		if (ticketId < 1 || ticketId > ticketList.size()) {
			return null;
		}
		return ticketList.get(ticketId - 1);
	}
	
	/**
	 * Executes a given command on a ticket associated
	 * with the given ticketId.
	 * 
	 * @param ticketId id of desired ticket
	 * @param command command to be executed
	 */
	public void executeCommand(int ticketId, Command command) {
		if (ticketId < 1 || ticketId > ticketList.size() || command == null) {
			throw new IllegalArgumentException();
		}
		
		Ticket updatedTicket = ticketList.get(ticketId - 1);
		
		try {
			updatedTicket.update(command);
		} catch (UnsupportedOperationException uoe) {
			throw new IllegalArgumentException();
		}
		
		ArrayList<String> notes = new ArrayList<String>();
		Scanner scan = new Scanner(updatedTicket.getNotes());
		while (scan.hasNextLine()) {
			String note = scan.nextLine();
			if ("\n".equals(note)) {
				break;
			}
			notes.add(note.substring(1));
		}
		
		String code = null;
		if (updatedTicket.getFeedbackCode() != null) {
			code = updatedTicket.getFeedbackCode();
		}
		if (updatedTicket.getResolutionCode() != null) {
			code = updatedTicket.getResolutionCode();
		}
		if (updatedTicket.getCancellationCode() != null) {
			code = updatedTicket.getCancellationCode();
		}
		
		Ticket replaceTicket = new Ticket(ticketId, updatedTicket.getState(), updatedTicket.getTicketTypeString(), updatedTicket.getSubject(), updatedTicket.getCaller(), updatedTicket.getCategory(), updatedTicket.getPriority(), updatedTicket.getOwner(), code, notes);
		
		ticketList.set(ticketId - 1, replaceTicket);
	}
	
	/**
	 * Deletes a ticket from the list based on the given id
	 * 
	 * @param ticketId id of desired ticket
	 */
	public void deleteTicketById(int ticketId) {
		if (ticketId < 1 || ticketId > ticketList.size()) {
			throw new IllegalArgumentException();
		}
		
		ticketList.remove(ticketId - 1);
	}
}
