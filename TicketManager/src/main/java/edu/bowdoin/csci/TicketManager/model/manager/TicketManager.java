package edu.bowdoin.csci.TicketManager.model.manager;


/**
 * Concrete class that maintains the 'TicketList' and handles 'Commands' from the GUI.
 * Implements the Singleton Design Pattern.
 * Controls the creation and modification of TicketLists
 * @author mmartinez
 *
 */
public class TicketManager {
	
	/**
	 * Constructor for TicketManager.
	 * Note: There can only be one instance of this object as part
	 * of the Singleton Design Pattern.
	 */
	private TicketManager() {
		
	}
	
	/** 
	 * Gets the single instance of the TicketManager
	 * 
	 * @return TicketManager instance
	 */
	public static TicketManager getInstance() {
		return null;
	}
	
	/** 
	 * Saves an active ticket list into a given file
	 * 
	 * @param filename file user saves ticket list to
	 * @throws IllegalArgumentException if it catches an IOException
	 */
	public void saveTicketsToFile(String filename) {
		// If TicketWriter throws an IOException
		// 		catch and throw IllegalArgumentException
	}
	
	/** 
	 * Loads a ticket list from a given file
	 * 
	 * @param filename file user loads ticket list from
	 * @throws IllegalException if it catches an IOException
	 */
	public void loadTicketsFromFile(String filename) {
		// If TicketReader throws an IOException
		// 		catch and throw IllegalArgumentException
	}
	
	/** 
	 * Updates the global TicketList reference to point to a new,
	 * empty TicketList object. The old TicketList will be garbage
	 * collected. We will assume that the user will have saved the 
	 * old TicketList (if wanted) before calling this operation.
	 */
	public void createNewTicketList() {
		
	}
	
	/** 
	 * Builds a 2D String array to display tickets in rows and 
	 * each ticket's information in columns. Array should have
	 * 1 row for every ticket and 5 columns.
	 * 
	 * @return a String representation of ticket list
	 */
	public String[][] getTicketsForDisplay() {
		return null;
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
		
		return null;
	}
	
	/**
	 * Gets a ticket from list associated with a given ticketId
	 * 
	 * @param ticketId id of desired ticket
	 * @return the ticket associated with the ticketId
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
		
	}
	
	/**
	 * Deletes a ticket from list associated with a given ticketId.
	 * 
	 * @param ticketId id of desired ticket
	 */
	public void deleteTicketById(int ticketId) {
		
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
		
	}
}
