package edu.bowdoin.csci.TicketManager.model.ticket;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.command.Command.CancellationCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.ResolutionCode;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Glass box unit tests for the Ticket class. 
 * @author zbecker2
 *
 */
public class TicketTest {
	
	/** Example ticket for use in other tests */
	Ticket example = new Ticket(TicketType.INCIDENT, "Canvas Down", "Rose Clayton", Category.NETWORK, Priority.URGENT, "Students cannot log in to Canvas"); 
	/** Example notes list for use in other tests */
	ArrayList<String> notesList = new ArrayList<String>(); 
	/**
	 * Add notes to notes_list for testing
	 */
	@BeforeEach
	public void setup() {
		notesList.add("Note 1."); 
		notesList.add("Note 2."); 
		notesList.add("Note 3."); 
		
		// For testing the FSM; necessary to test the id of a Ticket
		Ticket.setCounter(1);
	}
	
	
	
	/**
	 * Ensure that constructor throws IAE for null arguments and empty strings. 
	 */
	@Test
	public void testNewTicketConstructorInvalidValues() {
		try {
			Ticket t = new Ticket(null, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		
		try {
			Ticket t = new Ticket(TicketType.INCIDENT, "", "Andrea R.", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			Ticket t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			Ticket t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", null, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			Ticket t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, null, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			Ticket t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, Priority.HIGH, ""); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
	}
	
	/**
	 * Test that new ticket constructor works properly. 
	 */
	@Test
	public void testNewTicketConstructor() {
		/* Counter should increment by one after each ticket is requested */
		Ticket one = new Ticket(TicketType.REQUEST, "Please add more CS professors", "cs_major", Category.INQUIRY, Priority.MEDIUM, "Course registration. Oof.");
		Ticket two = new Ticket(TicketType.INCIDENT, "Power outage at Moulton", "P. Bear", Category.HARDWARE, Priority.URGENT, "No foooooood"); 
		assertEquals(one.getTicketId() - two.getTicketId(), -1);
		
		/* Any uninitialized fields should be empty strings, null, or an empty object type as appropriate */
		assertEquals(one.getCancellationCode(), null); 
		assertEquals(one.getFeedbackCode(), null); 
		assertEquals(one.getResolutionCode(), null); 
		
		/* Ensure that non-null fields are initialized properly */ 
		assertEquals(one.getState(), Ticket.NEW_NAME); 
		assertEquals(one.getSubject(), "Please add more CS professors"); 
		assertEquals(one.getCaller(), "cs_major"); 
		assertEquals(one.getCategory(), Ticket.C_INQUIRY); 
		assertEquals(one.getPriority(), Ticket.P_MEDIUM); 
		assertEquals(one.getNotes(), "Course registration. Oof."); 
	}
	
	/**
	 * Test alternate constructor for tickets from file with invalid arguments. 
	 * Ensure that IAE is thrown if any of the rules in UC1 are broken. 
	 */
	@Test 
	public void testFileTicketConstructorInvalidValues() {
		
		/* ID must be integer >= 0 */
		try {
			Ticket fileTicket = new Ticket(-1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* Required arguments cannot be null or empty strings */
		try {
			Ticket fileTicket = new Ticket(1, null, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, null, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", null, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, null, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* Ticket must have an owner if state is Working, Feedback, Resolved, or Closed */ 
		try {
			Ticket fileTicket = new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* The ticket must have a feedback code of either "Awaiting Caller", "Awaiting Change", or "Awaiting Provider" when in the "Feedback" state. */
		try {
			Ticket fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* The ticket must have a resolution code of either "Completed", 
		 * "Not Completed", "Solved", "Workaround", "Not Solved", or "Caller 
		 * Closed" in the "Resolved" or "Closed" states. If the ticket is a 
		 * "Request", the code can only be "Completed", "Not Completed", or 
		 * "Caller Closed". If the ticket is an "Incident", the code can only 
		 * be "Solved", "Workaround", "Not Solved", or "Called Closed".
		 */
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_SOLVED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_WORKAROUND, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_SOLVED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_WORKAROUND, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_SOLVED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_SOLVED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_COMPLETED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_COMPLETED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* The ticket must have a cancellation code of either duplicate or inappropriate if in the canceled state */
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, notesList); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* There must be at least one note line for each ticket. */ 
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.CC_DUPLICATE, null); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			Ticket fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, new ArrayList<String>()); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* The note may include new line characters. The note text is processed until the next '-' or '*' character that starts a new line. This means that the '-' and '*' characters can appear in a note, 
		 * but not as the first character in a new line - that would be considered a new note.
		 */
	
	}
	
	
	/**
	 * Test alternate constructor for tickets from file with valid arguments. 
	 * Additionally, the Ticket.counter should be updated with Ticket.setCounter(id + 1)
	 *  IF, the incoming id is greater than the current value in Ticket.counter. 
	 */
	@Test
	public void testFileTicketConstructor() {
		Ticket.setCounter(0); 
		Ticket one = new Ticket(37, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "Homework regrade request", "student", Ticket.C_INQUIRY, Ticket.P_LOW, "grader", Command.F_CALLER, notesList); 
		assertEquals(one.getTicketId(), 37); 
		Ticket two = new Ticket(TicketType.INCIDENT, "Canvas Down", "Rose Clayton", Category.NETWORK, Priority.URGENT, "Students cannot log in to Canvas"); 
		assertEquals(two.getTicketId(), 38);
	}
	
	/**
	 * Test getCaller
	 */
	@Test
	public void testGetCaller() {
		assertEquals(example.getCaller(), "Rose Clayton"); 
	}
	
	/**
	 * Test getCancellationCode
	 */
	@Test
	public void testGetCancellationCode() {
		assertEquals(example.getCancellationCode(), null); 
	}
	
	/**
	 * Test getCategory
	 */
	@Test
	public void testGetCategory() {
		assertEquals(example.getCategory(), Ticket.C_NETWORK); 
	}
	
	/**
	 * Test getFeedbackCode
	 */
	@Test
	public void testGetFeedbackCode() {
		assertEquals(example.getFeedbackCode(), null); 
	}
	
	/**
	 * Test getNotes
	 */
	@Test
	public void testGetNotes() {
		assertEquals(example.getNotes(), "Students cannot log in to Canvas"); 
	}
	
	/**
	 * Test getOwner
	 */
	@Test
	public void testGetOwner() {
		assertEquals(example.getOwner(), "");
	}
	
	/**
	 * Test getPriority
	 */
	@Test
	public void testGetPriority() {
		assertEquals(example.getPriority(), Ticket.P_URGENT); 
	}
	
	/**
	 * Test getResolutionCode
	 */
	@Test
	public void testGetResolutionCode() {
		assertEquals(example.getResolutionCode(), null); 
	}
	
	/**
	 * Test getState
	 */
	@Test
	public void testGetState() {
		assertEquals(example.getState(), Ticket.NEW_NAME); 
	}
	
	/**
	 * Test getSubject
	 */
	@Test
	public void testGetSubject() {
		assertEquals(example.getSubject(), "Canvas Down"); 
	}
	
	/**
	 * Test getTicketId
	 */
	@Test
	public void testGetTicketId() {
		assertEquals(example.getTicketId(), 0); 
	}
	
	/**
	 * Test getTicketType
	 */
	@Test
	public void testGetTicketType() {
		assertEquals(example.getTicketType(), TicketType.INCIDENT); 
	}
	
	/**
	 * Test getTicketTypeString
	 */
	@Test
	public void testGetTicketTypeString() {
		assertEquals(example.getTicketTypeString(), Ticket.TT_INCIDENT); 
	}
	
	
	/**
	 *  Test toString (notes: assumes we are implementing by calling getNotes to print notes consistently) 
	 *  @TODO 
	 */
	@Test
	public void testToString() {
		assertEquals(example.toString(), "TODO Insert actual string representation here!"); 
	}
	
	/**
	 * Test updateCommand
	 * @TODO
	 */
	@Test
	public void testUpdateCommand() {
		assertEquals(0, 1); 
	}
	
	/**
	 * Tests transitions from New to both Working and Canceled States
	 */
	@Test
	public void testNewStateTransitions() {
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "Super Cool Note");
		Command toCanceled = new Command(CommandValue.CANCEL, "Mikey", null, null, CancellationCode.DUPLICATE, "Another Epic Note");
		
		Ticket newTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket newTicket2 = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Assertions.assertEquals("New", newTicket.getState(),
				"Newly created notes should have a state attribute as 'New', but does not.");
		
		// Test WorkingState transition
		newTicket.update(toWorking);
		Assertions.assertEquals("Working", newTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", newTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		
		// Test CanceledState transition
		newTicket2.update(toCanceled);
		Assertions.assertEquals("Canceled", newTicket2.getState(),
				"Updated ticket should have a state attribute as 'Canceled', but does not.");
		Assertions.assertEquals("Duplicate", newTicket2.getCancellationCode(),
				"Updated ticket should have a Cancellation Code 'Duplicate', but does not.");
		
		// Test invalid scenario
		Command invalidToWorking = new Command(CommandValue.PROCESS, "", null, null, null, "Not So Cool Note");
		Ticket newTicket3 = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		
		try {
			newTicket3.update(invalidToWorking);
			Assertions.fail(
					"Attempting to update a ticket state with an invalid command should throw an UOE, but didn't");
		} catch (UnsupportedOperationException e) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests transitions from Working to Feedback, Resolved, and Canceled States
	 */
	@Test
	public void testWorkingStateTransitions() {
		
	}
	
	/**
	 * Tests transitions from Feedback to Working, Resolved, and Canceled States
	 */
	@Test
	public void testFeedbackStateTransitions() {
		
	}
	
	/**
	 * Tests transitions from Resolved to Feedback, Working, Closed, and Canceled States
	 */
	@Test
	public void testResolvedStateTransitions() {
		
	}
	
	/**
	 * Tests transitions from Closed to Working State
	 */
	@Test
	public void testClosedStateTransition() {
		
	}
}
