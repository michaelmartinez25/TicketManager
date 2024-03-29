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
 * @author Zeb Becker
 * @author Michael Martinez
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
	 * Test new ticket construction with string constructor, then use getters
	 */
	@Test
	public void testStringConstructorAndGetters() {
		Ticket t = new Ticket(8, Ticket.NEW_NAME, Ticket.TT_REQUEST, "Subject A", "Caller", Ticket.C_NETWORK, Ticket.P_URGENT, "Owner B", null, notesList); 
		assertEquals(t.getTicketId(), 8); 
		assertEquals(t.getState(), Ticket.NEW_NAME); 
		assertEquals(t.getTicketTypeString(), Ticket.TT_REQUEST); 
		assertEquals(t.getTicketType(), TicketType.REQUEST); 
		assertEquals(t.getSubject(), "Subject A"); 
		assertEquals(t.getCaller(), "Caller"); 
		assertEquals(t.getCategory(), Ticket.C_NETWORK); 
		assertEquals(t.getPriority(), Ticket.P_URGENT); 
		assertEquals(t.getOwner(), "Owner B"); 
		assertEquals(t.getFeedbackCode(), null); 
		assertEquals(t.getCancellationCode(), null); 
		assertEquals(t.getResolutionCode(), null); 
	}
	
	
	/**
	 * Ensure that constructor throws IAE for null arguments and empty strings. 
	 */
	@Test
	public void testNewTicketConstructorInvalidValues() {
		Ticket t = new Ticket(TicketType.REQUEST, "Please add more CS professors", "cs_major", Category.INQUIRY, Priority.MEDIUM, "Course registration. Oof.");
		assertNotNull(t); 
		try {
			t = new Ticket(null, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		
		try {
			t = new Ticket(TicketType.INCIDENT, "", "Andrea R.", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "", Category.HARDWARE, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) { 
			//Exception expected; carry on
		}
		try {
			t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", null, Priority.HIGH, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, null, "Italian department espresso machine is broken"); 
			fail("Constructing a new ticket with a null value or empty string input should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			t = new Ticket(TicketType.INCIDENT, "Espresso Machine Broken", "Andrea R.", Category.HARDWARE, Priority.HIGH, ""); 
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
		assertEquals(one.getNotes(), "-Course registration. Oof." + System.lineSeparator()); 
	}
	
	/**
	 * Test alternate constructor for tickets from file with invalid arguments. 
	 * Ensure that IAE is thrown if any of the rules in UC1 are broken. 
	 */
	@Test 
	public void testFileTicketConstructorInvalidValues() {
		
		Ticket fileTicket = new Ticket(TicketType.REQUEST, "Please add more CS professors", "cs_major", Category.INQUIRY, Priority.MEDIUM, "Course registration. Oof.");
		assertNotNull(fileTicket); 
		/* ID must be integer >= 0 */
		try {
			fileTicket = new Ticket(-1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* Required arguments cannot be null or empty strings */
		try {
			fileTicket = new Ticket(1, null, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, null, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", null, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, null, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* Ticket must have an owner if state is Working, Feedback, Resolved, or Closed */ 
		try {
			fileTicket = new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* The ticket must have a feedback code of either "Awaiting Caller", "Awaiting Change", or "Awaiting Provider" when in the "Feedback" state. */
		try {
			fileTicket = new Ticket(1, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, notesList);
			fail();
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
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_SOLVED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_WORKAROUND, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_SOLVED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_WORKAROUND, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_SOLVED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_SOLVED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CLOSED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_COMPLETED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_NOT_COMPLETED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		
		/* The ticket must have a cancellation code of either duplicate or inappropriate if in the canceled state */
		try {
			fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.RC_COMPLETED, notesList);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, notesList); 
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* There must be at least one note line for each ticket. */ 
		try {
			fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.CC_DUPLICATE, null);
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		try {
			fileTicket = new Ticket(1, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", null, new ArrayList<String>());
			fail();
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on. 
		}
		
		/* The note may include new line characters. The note text is processed until the next 
		 * '-' or '*' character that starts a new line. This means that the '-' and '*' characters can appear in a note, 
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
		Ticket.setCounter(1); 
		Ticket one = new Ticket(37, Ticket.WORKING_NAME, Ticket.TT_REQUEST, "Homework regrade request", "student", Ticket.C_INQUIRY, Ticket.P_LOW, "grader", null, notesList); 
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
		String expected = "-Students cannot log in to Canvas" + System.lineSeparator();
		assertEquals(example.getNotes(), expected); 
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
		Ticket fileTicket = new Ticket(33, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, "Broken rigger on Dowse", "Jumpy", Ticket.C_HARDWARE, Ticket.P_LOW, "Coxswain", Command.F_PROVIDER, notesList); 

		assertEquals(33, fileTicket.getTicketId()); 
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
		String expected = "Ticket ID: 1" + System.lineSeparator(); 
		expected += "Subject: Canvas Down" + System.lineSeparator(); 
		expected += "Category: Network" + System.lineSeparator();
		expected += "Priority: Urgent" + System.lineSeparator(); 
		expected += "Current state: New" + System.lineSeparator(); 
		expected += "Type: Incident" + System.lineSeparator(); 
		expected += "Caller: Rose Clayton" + System.lineSeparator(); 
		expected += "Owner: " + System.lineSeparator();
		expected += "Notes: " + System.lineSeparator() + "-Students cannot log in to Canvas" + System.lineSeparator();
		
		assertEquals(expected, example.toString()); 
		
	}
	
	/**
	 * Tests transitions from New to both Working and Canceled States
	 */
	@Test
	public void testNewStateTransitions() {
		//Transition Commands
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
		
		// Test invalid scenarios
		Command invalidCommand = new Command(CommandValue.FEEDBACK, "Not Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Not So Cool Note");
		Ticket newTicket3 = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		
		try {
			newTicket3.update(null);
			Assertions.fail(
					"Attempting to update a ticket state with a null command should throw UOE, but didn't");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
		
		try {
			newTicket3.update(invalidCommand);
			Assertions.fail(
					"Attempting to update a ticket state with an invalid command should throw an UOE, but didn't");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests transitions from Working to Feedback, Resolved, and Canceled States
	 */
	@Test
	public void testWorkingStateTransitions() {
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
		// Working transition commands
		Command toFeedback = new Command(CommandValue.FEEDBACK, "Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Super Cool Note");
		Command toResolvedInc = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.SOLVED, null, "Another Cool Note");
		Command toResolvedReq = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.COMPLETED, null, "Epic Note");
		Command toCanceled = new Command(CommandValue.CANCEL, "Mikey", null, null, CancellationCode.DUPLICATE, "Yet Another Epic Note");
		
		Ticket toFeedbackTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Assertions.assertEquals("New", toFeedbackTicket.getState(),
				"Newly created notes should have a state attribute as 'New', but does not.");
		Ticket toResolvedIncTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket toResolvedReqTicket = new Ticket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket toCanceledTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		
		// Test FeedbackState transition
		toFeedbackTicket.update(toWorking);
		Assertions.assertEquals("Working", toFeedbackTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toFeedbackTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toFeedbackTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toFeedbackTicket.getState(),
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toFeedbackTicket.getFeedbackCode(),
				"Updated ticket should have a Feedback Code 'Awaiting caller', but does not.");
		
		// Test ResolvedState transitions (for both Incident and Request Tickets)
		toResolvedIncTicket.update(toWorking);
		Assertions.assertEquals("Working", toResolvedIncTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toResolvedIncTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toResolvedIncTicket.update(toResolvedInc);
		Assertions.assertEquals("Resolved", toResolvedIncTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toResolvedIncTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Solved', but does not."); 
		
		
		toResolvedReqTicket.update(toWorking);
		Assertions.assertEquals("Working", toResolvedReqTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toResolvedReqTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toResolvedReqTicket.update(toResolvedReq);
		Assertions.assertEquals("Resolved", toResolvedReqTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Completed", toResolvedReqTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Completed', but does not.");
		
		
		// Test CanceledState transition
		toCanceledTicket.update(toWorking);
		Assertions.assertEquals("Working", toCanceledTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toCanceledTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toCanceledTicket.update(toCanceled);
		Assertions.assertEquals("Canceled", toCanceledTicket.getState(),
				"Updated ticket should have a state attribute as 'Canceled', but does not.");
		Assertions.assertEquals("Duplicate", toCanceledTicket.getCancellationCode(),
				"Updated ticket should have a Cancellation Code 'Duplicate', but does not.");
		
		// Test invalid scenarios
		Ticket invalidTestTicketInc = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket invalidTestTicketReq = new Ticket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		invalidTestTicketInc.update(toWorking);
		
		try {
			invalidTestTicketInc.update(null);
			Assertions.fail(
					"Attempting to update a ticket state with a null command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
		
		try {
			invalidTestTicketInc.update(toWorking);
			Assertions.fail(
					"Attempting to update a Working ticket with an invalid command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			//Exception expected, carry on
		}
		
		try {
			invalidTestTicketInc.update(toResolvedReq);
			Assertions.fail(
					"Attempting to update a Working ticket with an invalid Resolved command should throw an UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
		
		try {
			invalidTestTicketReq.update(toResolvedInc);
			Assertions.fail(
					"Attempting to update a Working ticket with an invalid Resolved command should throw an UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on
		}
	}
	
	/**
	 * Tests transitions from Feedback to Working, Resolved, and Canceled States
	 */
	@Test
	public void testFeedbackStateTransitions() {
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
		Command toFeedback = new Command(CommandValue.FEEDBACK, "Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Super Cool Note");
		// Feedback transition commands
		Command toReopen = new Command(CommandValue.REOPEN, "Mikey", null, null, null, "A Boring Note");
		Command toResolvedInc = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.SOLVED, null, "Another Cool Note");
		Command toResolvedReq = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.COMPLETED, null, "Epic Note");
		Command toCanceled = new Command(CommandValue.CANCEL, "Mikey", null, null, CancellationCode.DUPLICATE, "Yet Another Epic Note");
		
		Ticket toReopenTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Assertions.assertEquals("New", toReopenTicket.getState(),
				"Newly created notes should have a state attribute as 'New', but does not.");
		Ticket toResolvedIncTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket toResolvedReqTicket = new Ticket(TicketType.REQUEST, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket toCanceledTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		
		// Test Reopen Transition back to Working
		toReopenTicket.update(toWorking);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toReopenTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toReopenTicket.getFeedbackCode(),
				"Updated ticket should have a Feedback Code 'Awaiting Caller', but does not.");
		toReopenTicket.update(toReopen);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		
		// Test ResolvedState 
		toResolvedIncTicket.update(toWorking);
		Assertions.assertEquals("Working", toResolvedIncTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toResolvedIncTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toResolvedIncTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toResolvedIncTicket.getState(),
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toResolvedIncTicket.getFeedbackCode(),
				"Updated ticket should have a Feedback Code 'Awaiting Caller', but does not.");
		toResolvedIncTicket.update(toResolvedInc);
		Assertions.assertEquals("Resolved", toResolvedIncTicket.getState(), 
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toResolvedIncTicket.getResolutionCode(), 
				"Updated ticket should have a Resolution Code 'Solved', but does not.");
		
		toResolvedReqTicket.update(toWorking);
		Assertions.assertEquals("Working", toResolvedReqTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toResolvedReqTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toResolvedReqTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toResolvedReqTicket.getState(),
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toResolvedReqTicket.getFeedbackCode(),
				"Updated ticket should have a Feedback Code 'Awaiting Caller', but does not.");
		toResolvedReqTicket.update(toResolvedReq);
		Assertions.assertEquals("Resolved", toResolvedReqTicket.getState(), 
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Completed", toResolvedReqTicket.getResolutionCode(), 
				"Updated ticket should have a Resolution Code 'Completed', but does not.");
		
		// Test CanceledState
		toCanceledTicket.update(toWorking);
		Assertions.assertEquals("Working", toCanceledTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toCanceledTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toCanceledTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toCanceledTicket.getState(), 
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toCanceledTicket.getFeedbackCode(), 
				"Updated ticket should have a Feedback Code 'Awaiting Caller");
		toCanceledTicket.update(toCanceled);
		Assertions.assertEquals("Canceled", toCanceledTicket.getState(),
				"Updated ticket should have a state attribute as 'Canceled', but does not.");
		Assertions.assertEquals("Duplicate", toCanceledTicket.getCancellationCode(),
				"Updated ticket should have a Cancellation Code 'Duplicate', but does not.");
		
		//Test Invalid Scenarios
		Ticket invalidTestTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		invalidTestTicket.update(toWorking);
		invalidTestTicket.update(toFeedback);
		
		try {
			invalidTestTicket.update(null);
			Assertions.fail(
					"Attempting to update a ticket state with a null command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
		
		try {
			invalidTestTicket.update(toWorking);
			Assertions.fail(
					"Attempting to update a ticket state with an invalid command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
		
	}
	
	/**
	 * Tests transitions from Resolved to Feedback, Working, Closed, and Canceled States
	 */
	@Test
	public void testResolvedStateTransitions() {  
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
		Command toResolved = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.SOLVED, null, "Another Cool Note");
		// Transition Commands
		Command toFeedback = new Command(CommandValue.FEEDBACK, "Mikey", FeedbackCode.AWAITING_CALLER, null, null, "Super Cool Note");
		Command toReopen = new Command(CommandValue.REOPEN, "Mikey", null, null, null, "A Boring Note");
		Command toClosed = new Command(CommandValue.CONFIRM, "Mikey", null, null, null, "A Not-So-Boring Note");
		
		Ticket toFeedbackTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Assertions.assertEquals("New", toFeedbackTicket.getState(),
				"Newly created notes should have a state attribute as 'New', but does not.");
		Ticket toReopenTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Ticket toClosedTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		
		// Test FeedbackState
		toFeedbackTicket.update(toWorking);
		Assertions.assertEquals("Working", toFeedbackTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toFeedbackTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toFeedbackTicket.update(toResolved);
		Assertions.assertEquals("Resolved", toFeedbackTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toFeedbackTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Solved', but does not.");
		toFeedbackTicket.update(toFeedback);
		Assertions.assertEquals("Feedback", toFeedbackTicket.getState(), 
				"Updated ticket should have a state attribute as 'Feedback', but does not.");
		Assertions.assertEquals("Awaiting Caller", toFeedbackTicket.getFeedbackCode(), 
				"Updated ticket should have a Feedback Code 'Awaiting Caller");
		
		// Test WorkingState
		toReopenTicket.update(toWorking);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toReopenTicket.update(toResolved);
		Assertions.assertEquals("Resolved", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toReopenTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Solved', but does not.");
		toReopenTicket.update(toReopen);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		
		// Test ClosedState
		toClosedTicket.update(toWorking);
		Assertions.assertEquals("Working", toClosedTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toClosedTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toClosedTicket.update(toResolved);
		Assertions.assertEquals("Resolved", toClosedTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toClosedTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Solved', but does not.");
		toClosedTicket.update(toClosed);
		Assertions.assertEquals("Closed", toClosedTicket.getState(), 
				"Updated ticket should have a state attribute as 'Closed', but does not.");
		
		// Test Invalid Scenarios
		Ticket invalidTestTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		invalidTestTicket.update(toWorking);
		invalidTestTicket.update(toResolved);
		
		try {
			invalidTestTicket.update(null);
			Assertions.fail(
					"Attempting to update a ticket state with a null command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
		
		try {
			invalidTestTicket.update(toWorking);
			Assertions.fail(
					"Attempting to update a ticket state with an invalid command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
		
	}
	
	/**
	 * Tests transitions from Closed to Working State
	 */
	@Test
	public void testClosedStateTransition() {
		Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
		Command toResolved = new Command(CommandValue.RESOLVE, "Mikey", null, ResolutionCode.SOLVED, null, "Another Cool Note");
		Command toClosed = new Command(CommandValue.CONFIRM, "Mikey", null, null, null, "A Not-So-Boring Note");
		// Transition commands
		Command toReopen = new Command(CommandValue.REOPEN, "Mikey", null, null, null, "A Boring Note");
		
		Ticket toReopenTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		Assertions.assertEquals("New", toReopenTicket.getState(),
				"Newly created notes should have a state attribute as 'New', but does not.");
		
		// Test WorkingState 
		toReopenTicket.update(toWorking);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		toReopenTicket.update(toResolved);
		Assertions.assertEquals("Resolved", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Resolved', but does not.");
		Assertions.assertEquals("Solved", toReopenTicket.getResolutionCode(),
				"Updated ticket should have a Resolution Code 'Solved', but does not.");
		toReopenTicket.update(toClosed);
		Assertions.assertEquals("Closed", toReopenTicket.getState(), 
				"Updated ticket should have a state attribute as 'Closed', but does not.");
		toReopenTicket.update(toReopen);
		Assertions.assertEquals("Working", toReopenTicket.getState(),
				"Updated ticket should have a state attribute as 'Working', but does not.");
		Assertions.assertEquals("Mikey", toReopenTicket.getOwner(),
				"Updated ticket should have an owner 'Mikey', but does not.");
		
		// Test Invalid Scenarios
		Ticket invalidTestTicket = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
		invalidTestTicket.update(toWorking);
		invalidTestTicket.update(toResolved);
		invalidTestTicket.update(toClosed);
		
		try {
			invalidTestTicket.update(null);
			Assertions.fail(
					"Attempting to update a ticket state with a null command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
		
		try {
			invalidTestTicket.update(toWorking);
			Assertions.fail(
					"Attempting to update a ticket state with an invalid command should throw UOE, but didn't.");
		} catch (UnsupportedOperationException uoe) {
			// Exception expected, carry on.
		}
	}
	
	/**
	 * Tests transitions from canceled state (none are allowed).
	 */
	@Test
	public void testCanceledState() {
		try {
			Command toCanceled = new Command(CommandValue.CANCEL, "Mikey", null, null, CancellationCode.DUPLICATE, "Another Epic Note");
			Ticket t = new Ticket(TicketType.INCIDENT, "Subject", "Caller", Category.SOFTWARE, Priority.MEDIUM, "Note");
			t.update(toCanceled); 
			Command toWorking = new Command(CommandValue.PROCESS, "Mikey", null, null, null, "The OG Super Cool Note");
			t.update(toWorking);
			Assertions.fail("Attempting to update a canceled ticket should throw UOE, but didn't.");

		}
		catch (UnsupportedOperationException uoe) {
			//Exception expected; carry on
		}
	}
	
}
