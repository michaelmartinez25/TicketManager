package edu.bowdoin.csci.TicketManager.model.command;

/**
 * Command objects represent commands/user actions sent from the UI to the model. 
 * Commands cause the state of a ticket to update. 
 * @author zbecker2
 *
 */
public class Command {
	/** Caller */
	public static final String F_CALLER = ""; 
	/** Change */ 
	public static final String F_CHANGE = ""; 
	/** Provider */ 
	public static final String F_PROVIDER = ""; 
	/** resolution code: completed */ 
	public static final String RC_COMPLETED = ""; 
	/** resolution code: not completed */ 
	public static final String RC_NOT_COMPLETED = ""; 
	/** resolution code: solved */
	public static final String RC_SOLVED = ""; 
	/** resolution code: workaround */ 
	public static final String RC_WORKAROUND = ""; 
	/** resolution code: not solved */
	public static final String RC_NOT_SOLVED = ""; 
	/** resolution code: caller closed */ 
	public static final String RC_CALLER_CLOSED = ""; 
	/** cancellation code: duplicate */
	public static final String CC_DUPLICATE = ""; 
	/** cancellation code: inappropriate */
	public static final String CC_INAPPROPRIATE = ""; 
	/** ID of the person who is responsible for (owns) this ticket */
	private String ownerId;
	/** note on this command */ 
	private String note; 
	
	/** type of command */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }
	/** feedback code options */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }
	/** resolution code options */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/** cancellation code options */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
	
	/**
	 * Constructor
	 * @param c 
	 * @param ownerId
	 * @param feedbackCode
	 * @param resolutionCode
	 * @param cancellationCode
	 * @param note
	 */
	public Command(CommandValue c, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		
	}
	
	/**
	 * 
	 * @return command value/type of this command
	 */
	public CommandValue getCommand() {
		return null;
	}
	
	/**
	 * 
	 * @return ID of owner who is responsible for this ticket
	 */
	public String getOwnerId() {
		return ownerId; 
	}
	
	/**
	 * 
	 * @return resolution code of command, if any
	 */
	public ResolutionCode getResolutionCode() {
		return null;
	}
	
	/**
	 * 
	 * @return note attached to this command, as a string 
	 */
	public String getNote() {
		return note; 
	}
	
	/**
	 * 
	 * @return feedback code this command is setting the code of the target ticket to
	 */
	public FeedbackCode getFeedbackCode() {
		return null; 
	}
	
	/**
	 * @return cancellation code this command is setting the code of the target ticket to
	 */
	public CancellationCode getCancellationCode() {
		return null;
	}
}
