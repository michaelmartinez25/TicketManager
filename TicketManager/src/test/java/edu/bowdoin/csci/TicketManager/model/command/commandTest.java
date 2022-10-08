package edu.bowdoin.csci.TicketManager.model.command;

import edu.bowdoin.csci.TicketManager.model.command.*;
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
public class commandTest {
	
	Command testC; 
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
		/** A command must have a CommandValue */
		try {
			Command c = new Command(null, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("Constructor with null command value should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae){
			//Exception expected; carry on
		}
		/** A PROCESS command must have a non-null, non-empty ownerId*/
		try {
			Command c = new Command(CommandValue.PROCESS, null, FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A PROCESS command with a null or empty ownerId should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		try {
			Command c = new Command(CommandValue.PROCESS, "", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A PROCESS command with a null or empty ownerId should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on 
		}
		/** FEEDBACK command must have a feedback code */ 
		try {
			Command c = new Command(CommandValue.FEEDBACK, "ownerIsZeb", null, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("A FEEDBACK command with a null feedback code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on 
		}
		/** RESOLVE command must have a resolution code */ 
		try {
			Command c = new Command(CommandValue.RESOLVE, "ownerIsZeb", null, null, null, "Very Good Note TM"); 
			fail("A RESOLVE command with a null resolution code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		/** CANCEL command must have a cancellation code */ 
		try {
			Command c = new Command(CommandValue.CANCEL, "ownerIsZeb", null, null, null, "Very Good Note TM"); 
			fail("A CANCEL command with a null feedback code should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected; carry on
		}
		/** Cannot have empty string or null note */ 
		try {
			Command c = new Command(CommandValue.CANCEL, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.CALLER_CLOSED, CancellationCode.DUPLICATE, ""); 
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
	 * Test getter for ResolutionCode
	 */
	@Test
	public void testGetResolutionCode() {
		assertEquals(testC.getResolutionCode(), ResolutionCode.WORKAROUND); 
	}
	
	/**
	 * Test getter for Note (returns note as String
	 */
	@Test
	public void testGetNote() {
		assertEquals(testC.getNote(), "Very Good Note TM"); 
	}
	
	/**
	 * Test getter for FeedbackCode
	 */
	@Test
	public void testGetFeedbackCode() {
		assertEquals(testC.getFeedbackCode(), FeedbackCode.AWAITING_CHANGE); 
	}
	
	/**
	 * Test getter for CancellationCode
	 */
	@Test
	public void testGetCancellationCode() {
		assertEquals(testC.getCancellationCode(), CancellationCode.INAPPROPRIATE); 
	}
}
