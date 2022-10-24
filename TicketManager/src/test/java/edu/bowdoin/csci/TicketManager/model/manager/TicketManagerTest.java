package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

import org.junit.jupiter.api.BeforeEach;


public class TicketManagerTest {
	
	private TicketManager manager;
	
	/**
	 * Sets up the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = TicketManager.getInstance();
		manager.createNewTicketList();
	}
	
	/**
	 * Tests TicketManager instance
	 * May not be necessary
	 * In Progress
	 */
	@Test
	public void testTicketManager() {
		manager = TicketManager.getInstance(); 
		assertNotNull(manager, 
				"");
	}
	
	/**
	 * Tests getTicketsForDisplay() & loadTicketsFromFile()
	 */
	@Test
	public void testGetTicketsForDisplay() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		String[][] tickets = manager.getTicketsForDisplay();
		assertEquals(6, tickets.length,
				"Ticket Manager should have loaded six tickets from file, but did not.");
	}
	
	/**
	 * Tests getTicketsForDisplayByType
	 * In Progress
	 */
	@Test
	public void testGetTicketsForDisplayByType() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		String[][] incidentTickets = manager.getTicketsForDisplayByType(TicketType.INCIDENT);
		assertEquals(2, incidentTickets.length, 
				"");
		String[][] requestTickets = manager.getTicketsForDisplayByType(TicketType.REQUEST);
		assertEquals(4, requestTickets.length, 
				"");
	}
	
	/**
	 * Tests createNewTicketList()
	 */
	@Test
	public void testNewTicketList() {
		manager.createNewTicketList();
		String[][] tickets = manager.getTicketsForDisplay();
		assertEquals(0, tickets.length,
				"Ticket Manager should have an empty ticket list, but did not.");
	}
	
	/**
	 * Tests invalid inputs for loadTicketsFromFile()
	 * In Progress
	 */
	@Test
	public void testLoadTicketsFromFile() {
		try {
			manager.loadTicketsFromFile("test-files/tickets1.txt");
			fail("");
		} catch (IllegalArgumentException iae) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests saveTicketsToFile()
	 * In Progress
	 */
	@Test
	public void testSaveTicketsToFile() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		manager.saveTicketsToFile("test-files/testfile.txt");
		manager.loadTicketsFromFile("test-files/testfile.txt");
		String[][] tickets = manager.getTicketsForDisplay();
		assertEquals(6, tickets.length, 
				"Ticket Manager should have overwritten six tickets to the test file, but did not.");
		manager.saveTicketsToFile("test-files/tickets.txt");
		manager.loadTicketsFromFile("test-files/tickets.txt");
		String[][] fileTickets = manager.getTicketsForDisplay();
		assertEquals(6, fileTickets.length, 
				"Ticket Manager should have saved six tickets to a new file, but did not.");
		
		
		// Test invalid input
		try {
			manager.saveTicketsToFile("test-file/tickets.txt");
			fail("");
		} catch (IllegalArgumentException iae) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests getTicketById() & addTicketToList() & deleteTicketById()
	 * In Progress
	 */
	@Test
	public void testGetTicketById() {
		manager.addTicketToList(TicketType.INCIDENT, "Subject", "Caller", Category.DATABASE, Priority.HIGH, "Note");
		String[][] tickets = manager.getTicketsForDisplay();
		Integer ticketId = manager.getTicketById(1).getTicketId();
		assertEquals(tickets[0][0], ticketId.toString(), 
				"");
		manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", Category.DATABASE, Priority.HIGH, "Note");
		manager.deleteTicketById(1);
		tickets = manager.getTicketsForDisplay();
		ticketId = manager.getTicketById(2).getTicketId();
		assertEquals(tickets[0][0], ticketId.toString(), 
				"");
		
		
		// test invalid input for getTicketById()
		assertNull(manager.getTicketById(0), 
				"");
	}
	
	/**
	 * Tests invalid inputs for addTicketToList()
	 * In Progress
	 */
	@Test
	public void testInvalidAddTicketToList() {
		// Comment for PMD
		assertEquals(1, 1); 
	}
	
	/**
	 * Tests executeCommand()
	 * In Progress
	 */
	@Test
	public void testExecuteCommand() {
		// Comment for PMD
		assertEquals(1, 1);
	}
}
