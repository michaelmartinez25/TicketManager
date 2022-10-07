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
	 * Constructor should reject null command value 
	 */
	@Test
	public void testConstructorNullCV() {
		try {
			Command c = new Command(null, "ownerIsZeb", FeedbackCode.AWAITING_CALLER, ResolutionCode.NOT_SOLVED, CancellationCode.DUPLICATE, "Very Good Note TM"); 
			fail("Constructor with null command value should throw IAE, but this does not"); 
		}
		catch (IllegalArgumentException iae){
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

	
	
	
	
}
