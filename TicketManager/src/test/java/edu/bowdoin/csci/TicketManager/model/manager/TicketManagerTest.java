package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

import org.junit.jupiter.api.BeforeEach;

/**
 * Glass Box Unit Tests for TicketManager
 * @author Michael Martinez
 */
public class TicketManagerTest {
	
	/** Stores the one and only instance of TicketManager */
	private TicketManager manager;
	
	/**
	 * Sets up the test.
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = TicketManager.getInstance();
		manager.createNewTicketList();
	}
	
	/**
	 * Tests TicketManager instance
	 */
	@Test
	public void testTicketManager() {
		assertNotNull(manager, 
				"The TicketManager object should not be null, but was.");
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
	 */
	@Test
	public void testGetTicketsForDisplayByType() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		String[][] incidentTickets = manager.getTicketsForDisplayByType(TicketType.INCIDENT);
		assertEquals(2, incidentTickets.length, 
				"There should be two incident tickets to display.");
		String[][] requestTickets = manager.getTicketsForDisplayByType(TicketType.REQUEST);
		assertEquals(4, requestTickets.length, 
				"There should be four request tickets to display.");
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
	 */
	@Test
	public void testInvalidLoadTicketsFromFile() {
		try {
			manager.loadTicketsFromFile("test-files/tickets1.txt");
			fail("Attempting to load tickets from an invalid file should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests saveTicketsToFile()
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
			fail("Attempting to save tickets to an invalid file should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests getTicketById() & addTicketToList() & deleteTicketById()
	 */
	@Test
	public void testGetTicketById() {
		manager.addTicketToList(TicketType.INCIDENT, "Subject", "Caller", Category.DATABASE, Priority.HIGH, "Note");
		String[][] tickets = manager.getTicketsForDisplay();
		Integer ticketId = manager.getTicketById(1).getTicketId();
		assertEquals(tickets[0][0], ticketId.toString(), 
				"The first ticket's id should be 1, but wasn't.");
		manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", Category.DATABASE, Priority.HIGH, "Note");
		manager.deleteTicketById(1);
		tickets = manager.getTicketsForDisplay();
		ticketId = manager.getTicketById(2).getTicketId();
		assertEquals(tickets[0][0], ticketId.toString(), 
				"The first ticket's id should be 2, but wasn't.");
		
		
		// test invalid input for getTicketById()
		assertNull(manager.getTicketById(0), 
				"Invalid inputs for getTicketById should return null, but did not.");
	}
	
	/**
	 * Tests invalid inputs for addTicketToList()
	 */
	@Test
	public void testInvalidAddTicketToList() {
		try {
			manager.addTicketToList(null, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the TicketType field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, null, "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Subject field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "", "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Subject field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", null, Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Caller field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", "", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Caller field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", null, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Category field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, null, "Note");
			Assertions.fail(
					"Attempting to leave the Priority field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, null);
			Assertions.fail(
					"Attempting to leave the Note field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			manager.addTicketToList(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, "");
			Assertions.fail(
					"Attempting to leave the Note field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
	}
	
	/**
	 * Tests executeCommand()
	 */
	@Test
	public void testExecuteCommand() {
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
		Command toFeedback = new Command(CommandValue.FEEDBACK, "Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Super Cool Note");
		
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		
		manager.executeCommand(1, toWorking);
		Assertions.assertEquals("Working", manager.getTicketById(1).getState(), 
				"Ticket 1 should be updated to the Working state, but did not.");
		
		manager.executeCommand(2, toFeedback);
		Assertions.assertEquals("Feedback", manager.getTicketById(2).getState(), 
				"Ticket 2 should be updated to the Feedback state, but did not.");

		try {
			manager.executeCommand(1, null);
			Assertions.fail(
					"Attempting to execute a null command should throw IAE, but did not.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
		
		try {
			manager.executeCommand(1, toWorking);
			Assertions.fail(
					"Attempting to execute an invalid command should throw IAE, but did not.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
	}
}
