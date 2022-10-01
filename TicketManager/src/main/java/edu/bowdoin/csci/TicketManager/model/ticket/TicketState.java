package edu.bowdoin.csci.TicketManager.model.ticket;

import edu.bowdoin.csci.TicketManager.model.command.Command;

/**
 * Interface for states in the Ticket Manager State Pattern.  All 
 * concrete ticket states must implement the TicketState interface.
 * 
 * @author Sarah Heckman
 * @author Kai Presler-Marshall
 */
public interface TicketState {
	
	/**
	 * Update the Ticket based on the given Command.
	 * An UnsupportedOperationException is thrown if the CommandValue
	 * is not a valid action for the given state.  
	 * @param command Command describing the action that will update the Ticket's
	 * state.
	 * @throws UnsupportedOperationException if the CommandValue is not a valid action
	 * for the given state.
	 */
	void updateState(Command command);
	
	/**
	 * Returns the name of the current state as a String.
	 * @return the name of the current state as a String.
	 */
	String getStateName();

}