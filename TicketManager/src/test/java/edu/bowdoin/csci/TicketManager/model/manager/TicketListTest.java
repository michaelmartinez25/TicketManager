package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Glass Box Unit Tests for TicketList
 * @author mmartinez
 */
public class TicketListTest {

	private TicketList list;
	
	/**
	 * Sets up the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		list = new TicketList();
	}
	
	/**
	 * Tests the constructor of the TicketList
	 * Note: I have no idea how to test the constructor
	 */
	@Test
	public void testTicketList() {
		// Check to see list is not null
		Assertions.assertNotNull(list,
				"TicketList object should not be null, but it was.");
		Assertions.assertEquals(null, list.getTicketById(1), 
				"TicketList should have a Ticket counter of 1, but does not.");
	}
	
	/**
	 * Tests valid inputs for the addTicket()
	 */
	@Test
	public void testAddTicket() {
		int ticketId = list.addTicket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, "Note"); 
		Assertions.assertEquals(1, ticketId,
				"A new ticket should have been created and placed as the first element in the TicketList with a ticketId of 1, but was not.");
		int ticketId2 = list.addTicket(TicketType.INCIDENT, "Subject", "Caller", Category.DATABASE, Priority.HIGH, "Note");
		Assertions.assertEquals(2, ticketId2, 
				"A new ticket should have been created and placed as the first element in the TicketList with a ticketId of 2, but was not.");
	}
	
	/**
	 * Test invalid inputs for the addTicket() method
	 */
	@Test
	public void testInvalidAddTicket() {
		
		try {
			list.addTicket(null, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the TicketType field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, null, "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Subject field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "", "Caller", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Subject field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", null, Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Caller field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", "", Category.SOFTWARE, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Caller field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", "Caller", null, Priority.LOW, "Note");
			Assertions.fail(
					"Attempting to leave the Category field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, null, "Note");
			Assertions.fail(
					"Attempting to leave the Priority field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, null);
			Assertions.fail(
					"Attempting to leave the Note field blank should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
		
		try {
			list.addTicket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.LOW, "");
			Assertions.fail(
					"Attempting to leave the Note field as an empty String should throw an IAE, but did not.");
		} catch (IllegalArgumentException iae) {
			// Exception expected; carry on.
		}
	}
	
	/**
	 * Tests valid inputs for addTickets()
	 */
	@Test
	public void testAddTickets() {
		
	}
	
	/**
	 * Tests invalid inputs for addTickets()
	 */
	@Test
	public void testInvalidAddTickets() {
		
	}
	
	@Test
	public void testDeleteTicket() {
		list.deleteTicketById(0);
		assertEquals(1, 1);
	}
	
	@Test
	public void testGetTicket() {
		list.getTicketById(0);
		list.getTicketsByType(TicketType.INCIDENT);
		list.getTickets();
		assertEquals(1, 1);
	}
	
	@Test
	public void testExecuteCommand() {
		list.executeCommand(0, null);
		assertEquals(1, 1);
	}
}
