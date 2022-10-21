package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
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
	
//	/**
//	 * Tests valid and invalid inputs for addTickets()
//	 */
//	@Test
//	public void testAddTickets() {
//		List<Ticket> testTicketList = new ArrayList<Ticket>();
//		Ticket testTicket = new Ticket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//		Ticket testTicket2 = new Ticket(TicketType.REQUEST, "Subject2", "Caller2", Category.SOFTWARE, Priority.LOW, "Note2");
//		Ticket testTicket3 = new Ticket(TicketType.REQUEST, "Subject3", "Caller3", Category.SOFTWARE, Priority.LOW, "Note3");
//		
//		testTicketList.add(testTicket);
//		testTicketList.add(testTicket2);
//		testTicketList.add(testTicket3);
//		
//		list.addTickets(testTicketList);
//		Assertions.assertEquals(testTicket, list.getTicketById(1), 
//				"First element in TicketList should match testTicket, but did not.");
//		Assertions.assertEquals(testTicket2, list.getTicketById(2), 
//				"Second element in TicketList should match testTicket2, but did not.");
//		Assertions.assertEquals(testTicket3, list.getTicketById(3), 
//				"Third element in TicketList should match testTicket3, but did not.");
//		// Method should work even if TicketList already has tickets
//		list.addTickets(testTicketList);
//		Assertions.assertEquals(testTicket, list.getTicketById(4),
//				"Fourth element in TicketList should match testTicket, but did not.");
//		Assertions.assertEquals(testTicket2, list.getTicketById(5),
//				"Fifth element in TicketList should match testTicket2, but did not.");
//		Assertions.assertEquals(testTicket3, list.getTicketById(6),
//				"Sixth element in TicketList should match testTicket3, but did not.");
//		
//		
//		// Test invalid scenarios
//		List<Ticket> invalidTicketList = null;
//		try {
//			list.addTickets(invalidTicketList);
//			Assertions.fail(
//					"Attempting to add a null object should throw an IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			//Exception expected, carry on.
//		}
//		
//		invalidTicketList = new ArrayList<Ticket>();
//		try {
//			list.addTickets(invalidTicketList);
//			Assertions.fail(
//					"Attempting to add an empty ticketList should throw an IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			//Exception expected, carry on.
//		}
//		
//	}
	
//	/**
//	 * Tests getTickets(), getTicketsByType(), and getTicketById()
//	 */
//	@Test
//	public void testGetTickets() {
//		List<Ticket> testTicketList = new ArrayList<Ticket>();
//		Ticket testTicket = new Ticket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//		Ticket testTicket2 = new Ticket(TicketType.INCIDENT, "Subject2", "Caller2", Category.SOFTWARE, Priority.LOW, "Note2");
//		Ticket testTicket3 = new Ticket(TicketType.REQUEST, "Subject3", "Caller3", Category.SOFTWARE, Priority.LOW, "Note3");
//		Ticket testTicket4 = new Ticket(TicketType.INCIDENT, "Subject4", "Caller4", Category.SOFTWARE, Priority.LOW, "Note4");
//		
//		testTicketList.add(testTicket);
//		testTicketList.add(testTicket2);
//		testTicketList.add(testTicket3);
//		testTicketList.add(testTicket4);
//		
//		list.addTickets(testTicketList);
//		
//		// Test getTickets()
//		List<Ticket> retrievedTickets = list.getTickets();
//		Assertions.assertEquals(testTicketList, retrievedTickets,
//				"Retrieved Ticket List should match the testTicketList, but did not.");
//		
//		// Test getTicketsByType()
//		List<Ticket> testRequestTickets = new ArrayList<Ticket>();
//		testRequestTickets.add(testTicket);
//		testRequestTickets.add(testTicket3);
//		List<Ticket> testIncidentTickets = new ArrayList<Ticket>();
//		testIncidentTickets.add(testTicket2);
//		testIncidentTickets.add(testTicket4);
//		
//		List<Ticket> requestTickets = list.getTicketsByType(TicketType.REQUEST);
//		Assertions.assertEquals(testRequestTickets, requestTickets);
//		List<Ticket> incidentTickets = list.getTicketsByType(TicketType.INCIDENT);
//		Assertions.assertEquals(testIncidentTickets, incidentTickets);
//		
//		try {
//			list.getTicketsByType(null);
//			Assertions.fail(
//					"Attempting to get tickets of null type should throw IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on
//		}
//		
//		// Test getTicketById()
//		Assertions.assertEquals(testTicket, list.getTicketById(1), 
//				"First element in TicketList should match testTicket, but did not.");
//		Assertions.assertEquals(testTicket2, list.getTicketById(2), 
//				"Second element in TicketList should match testTicket2, but did not.");
//		Assertions.assertEquals(testTicket3, list.getTicketById(3), 
//				"Third element in TicketList should match testTicket3, but did not.");
//		Assertions.assertEquals(testTicket4, list.getTicketById(4),
//				"Fourth element in TicketList should match testTicket4, but did not.");
//		
//		Ticket invalidTicket = list.getTicketById(0);
//		Assertions.assertNull(invalidTicket, 
//				"An invalid TicketId should return null, but did not.");
//		
//		Ticket invalidTicket2 = list.getTicketById(5);
//		Assertions.assertNull(invalidTicket2,
//				"An invalid TicketId should return null, but did not.");
//	}
	
//	/**
//	 * Tests executeCommand()
//	 */
//	@Test
//	public void testExecuteCommand() {
//		Ticket testTicket = new Ticket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//		
//		list.addTicket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//
//		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
//		testTicket.update(toWorking);
//		
//		list.executeCommand(1, toWorking);
//		Assertions.assertEquals(testTicket.getState(), list.getTicketById(1).getState(),
//				"testTicket should be updated to Working state, but was not.");
//		
//		Command toFeedback = new Command(CommandValue.FEEDBACK, "Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Super Cool Note");
//		testTicket.update(toFeedback);
//		
//		list.executeCommand(1, toFeedback);
//		Assertions.assertEquals(testTicket.getState(), list.getTicketById(1).getState(),
//				"testTicket should be updated to Feedback state, but was not.");
//		
//		try {
//			list.executeCommand(0, toWorking);
//			Assertions.fail(
//					"Attempting to execute command on invalid index should throw IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on
//		}
//		
//		try {
//			list.executeCommand(2, toWorking);
//			Assertions.fail(
//					"Attempting to execute command on invalid index should throw IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on
//		}
//		
//		try {
//			list.executeCommand(1, null);
//			Assertions.fail(
//					"Attempting to execute a null command should throw IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on
//		}
//		
//		try {
//			list.executeCommand(1, toWorking);
//			Assertions.fail(
//					"Attempting to execute an invalid command should throw IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on
//		}
	}
	
//	/**
//	 * Tests deleteTicketById()
//	 */
//	@Test
//	public void testDeleteTicketById() {
//		List<Ticket> testTicketList = new ArrayList<Ticket>();
//		List<Ticket> testTicketList2 = new ArrayList<Ticket>();
//		List<Ticket> testTicketList3 = new ArrayList<Ticket>();
//		Ticket testTicket = new Ticket(TicketType.INCIDENT, "Subject2", "Caller2", Category.SOFTWARE, Priority.LOW, "Note2");
//		Ticket testTicket2 = new Ticket(TicketType.REQUEST, "Subject3", "Caller3", Category.SOFTWARE, Priority.LOW, "Note3");
//		Ticket testTicket3 = new Ticket(TicketType.INCIDENT, "Subject4", "Caller4", Category.SOFTWARE, Priority.LOW, "Note4");
//		
//		testTicketList.add(testTicket);
//		testTicketList.add(testTicket2);
//		testTicketList.add(testTicket3);
//		
//		testTicketList2.add(testTicket);
//		testTicketList2.add(testTicket3);
//		
//		testTicketList3.add(testTicket);
//		
//		list.addTicket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//		list.addTickets(testTicketList);
//		
//		list.deleteTicketById(1);
//		Assertions.assertEquals(testTicketList, list.getTickets(),
//				"First element in TicketList should have been deleted, but was not.");
//		
//		list.deleteTicketById(2);
//		Assertions.assertEquals(testTicketList2, list.getTickets(),
//				"Middle element in TicketList should have been deleted, but was not.");
//		
//		list.deleteTicketById(2);
//		Assertions.assertEquals(testTicketList3, list.getTickets(),
//				"Last element in TicketList should have been deleted, but was not.");
//	}
	
//	/**
//	 * Tests invalid scenarios for deleteTicketById()
//	 */
//	@Test
//	public void testInvalidDeleteTicketById() {
//		list.addTicket(TicketType.REQUEST, "Subject1", "Caller1", Category.SOFTWARE, Priority.LOW, "Note1");
//		
//		try {
//			list.deleteTicketById(0);
//			Assertions.fail(
//					"Attempting to remove from an invalid Id should throw an IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on.
//		}
//		
//		try {
//			list.deleteTicketById(2);
//			Assertions.fail(
//					"Attempting to remove from an invalid Id should throw an IAE, but did not.");
//		} catch (IllegalArgumentException iae) {
//			// Exception expected, carry on.
//		}
//	}
//}
