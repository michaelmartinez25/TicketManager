package edu.bowdoin.csci.TicketManager.model.command;

import edu.bowdoin.csci.TicketManager.model.command.Command.CancellationCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.ResolutionCode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Glass box unit tests for the command class. 
 * @author zbecker2
 *
 */
public class CommandTest {
	
	/** testC is a command for easily testing getter functions */
	private Command testC; 
	
	/**
	 * Create test command as set up for testing getters
	 */
	@BeforeEach
	public void setup() {
		CommandValue cv = CommandValue.CONFIRM; 
		FeedbackCode fc = FeedbackCode.AWAITING_CHANGE;
		CancellationCode cc = CancellationCode.INAPPROPRIATE; 
		ResolutionCode rc = ResolutionCode.WORKAROUND; 
		testC = new Command(cv, "ownerIsZeb", fc, rc, cc, "Very Good Note TM"); 
	}
	
	
	
	
	
	
	/**
	 * Constructor should reject invalid or missing arguments
	 */
	@Test
	public void testConstructorIllegalArguments() {
		
		/* A command must have a CommandValue */
		try {
			new Command(null, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("Constructor with null command value should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae){
			//Exception expected; carry on
		}
		/* A PROCESS command must have a non-null, non-empty ownerId*/
		try {
			new Command(CommandValue.PROCESS, null, FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A PROCESS command with a null or empty ownerId should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			new Command(CommandValue.PROCESS, "", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A PROCESS command with a null or empty ownerId should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on 
		}
		/* FEEDBACK command must have a feedback code */ 
		try {
			new Command(CommandValue.FEEDBACK, "ownerIsZeb", null, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A FEEDBACK command with a null feedback code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on 
		}
		/* RESOLVE command must have a resolution code */ 
		try {
			new Command(CommandValue.RESOLVE, "ownerIsZeb", null, null, null, "Very Good Note TM"); 
			fail("A RESOLVE command with a null resolution code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		/* CANCEL command must have a cancellation code */ 
		try {
			new Command(CommandValue.CANCEL, "ownerIsZeb", null, null, null, "Very Good Note TM"); 
			fail("A CANCEL command with a null feedback code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		/* Cannot have empty string or null note */ 
		try {
			new Command(CommandValue.CANCEL, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, ""); 
			fail("A command with empty or null note should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			new Command(CommandValue.CANCEL, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, null);
			fail("A command with empty or null note should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
	}
	
	/**
	 * Test getter for command (returns an option from CommandValue enum)
	 */
	@Test 
	public void testGetCommand() {
		assertEquals(testC.getCommand(), CommandValue.CONFIRM); 
	}
	
	/**
	 * Test getter for ownerId (returns ownerId as string)
	 */
	@Test
	public void testGetOwnerId() {
		assertEquals(testC.getOwnerId(), "ownerIsZeb"); 
	}
	
	/**
	 * Test getter for Note (returns note as String
	 */
	@Test
	public void testGetNote() {
		assertEquals(testC.getNote(), "Very Good Note TM"); 
	}
	
	/**
	 * Test getter for ResolutionCode
	 */
	@Test
	public void testGetResolutionCode() {
		Command c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.CALLER_CLOSED, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_CALLER_CLOSED); 
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.COMPLETED, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_COMPLETED); 
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.NOT_COMPLETED, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_NOT_COMPLETED); 
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.SOLVED, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_SOLVED); 
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.NOT_SOLVED, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_NOT_SOLVED); 
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.WORKAROUND, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), Command.RC_WORKAROUND); 
		c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, null, null, "Very Good Note TM");
		assertEquals(c.getResolutionCode(), null); 
	}
	
	/**
	 * Test getter for FeedbackCode
	 */
	@Test
	public void testGetFeedbackCode() {
		Command c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", FeedbackCode.AWAITING_CHANGE, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "Very Good Note TM");
		assertEquals(c.getFeedbackCode(), Command.F_CHANGE);
		c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "Very Good Note TM");
		assertEquals(c.getFeedbackCode(), Command.F_CALLER);
		c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", FeedbackCode.AWAITING_PROVIDER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "Very Good Note TM");
		assertEquals(c.getFeedbackCode(), Command.F_PROVIDER);
		c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, ResolutionCode.CALLER_CLOSED, null, "Very Good Note TM"); 
		assertEquals(c.getFeedbackCode(), null);
	}
	
	/**
	 * Test getter for CancellationCode
	 */
	@Test
	public void testGetCancellationCode() {
		Command c = new Command(CommandValue.CANCEL, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, "Very Good Note TM");
		assertEquals(c.getCancellationCode(), Command.CC_DUPLICATE); 
		c = new Command(CommandValue.CANCEL, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.INAPPROPRIATE, "Very Good Note TM");
		assertEquals(c.getCancellationCode(), Command.CC_INAPPROPRIATE); 
		c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, null, null, "Very Good Note TM");
		assertEquals(c.getCancellationCode(), null);
	}
}
