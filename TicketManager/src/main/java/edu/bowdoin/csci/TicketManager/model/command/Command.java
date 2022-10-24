package edu.bowdoin.csci.TicketManager.model.command;

/**
 * Command objects represent commands/user actions sent from the UI to the model. 
 * Commands cause the state of a ticket to update. 
 * @author Zeb Becker
 *
 */
public class Command {
	/** String representation for feedback code  */
	public static final String F_CALLER = "Awaiting Caller";
	/** String representation for feedback code */ 
	public static final String F_CHANGE = "Awaiting Change"; 
	/** String representation for feedback code */ 
	public static final String F_PROVIDER = "Awaiting Provider"; 
	/** String representation for resolution code */ 
	public static final String RC_COMPLETED = "Completed"; 
	/** String representation for resolution code */ 
	public static final String RC_NOT_COMPLETED = "Not Completed"; 
	/** String representation for resolution code */
	public static final String RC_SOLVED = "Solved"; 
	/** String representation for resolution code */ 
	public static final String RC_WORKAROUND = "Workaround"; 
	/** String representation for resolution code */
	public static final String RC_NOT_SOLVED = "Not Solved"; 
	/** String representation for resolution code */ 
	public static final String RC_CALLER_CLOSED = "Caller Closed"; 
	/** String representation for cancellation code */
	public static final String CC_DUPLICATE = "Duplicate"; 
	/** String representation for cancellation code */
	public static final String CC_INAPPROPRIATE = "Inappropriate"; 
	
	/** ID of the person who is responsible for (owns) this ticket */
	private String ownerId;
	/** note on this command */ 
	private String note; 
	/** Command value for this command */
	private CommandValue cv; 
	/** Feedback code for this command */ 
	private FeedbackCode fc; 
	/** Resolution code for this command */ 
	private ResolutionCode rc; 
	/** CancellationCode for this command */ 
	private CancellationCode cc; 
	
	/** Enumerate possible types of command */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }
	/** Enumerate feedback code options */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }
	/** Enumerate resolution code options */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/** Enumerate cancellation code options */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
	
	/**
	 * Constructor
	 * @param c type of command
	 * @param ownerId String noting who is owns this ticket 
	 * @param feedbackCode feedback code (set to null if CommandValue is not FEEDBACK)
	 * @param resolutionCode resolution code (set to null if CommandValue is not RESOLVE)
	 * @param cancellationCode cancellation code (set to null if CommandValue is not CANCEL)
	 * @param note to add to ticket
	 * @throws Illegal Argument Exception if any required argument is not valid. 
	 * 			Note that the required arguments can change based on the type of CommandValue: 
	 * 			If it is Feedback, feedback code is required. If Resolve, resolution code is required. If Cancel, cancellation code is required.
	 * 	 */
	public Command(CommandValue c, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		
		IllegalArgumentException iae = new IllegalArgumentException(); 
		
		if (c == null || "".equals(note) || note == null) {
			throw iae; 
		}
		
		if (c == CommandValue.PROCESS && (ownerId == null || "".equals(ownerId))) {
			throw iae; 
		}
		
		this.cv = c; 
		this.fc = null;
		this.rc = null;
		this.cc = null;
		
		
		if (c == CommandValue.FEEDBACK) {
			if (feedbackCode == null) {
				throw iae; 
			}
			else {
				fc = feedbackCode; 
			} 
		}
		
		if (c == CommandValue.RESOLVE) {
			if (resolutionCode == null) {
				throw iae; 
			}
			else {
				rc = resolutionCode; 
			}
		}
		
		if (c == CommandValue.CANCEL)
			if (cancellationCode == null) {
				throw iae; 
			}
			else {
				cc = cancellationCode; 
			}
		
		this.ownerId = ownerId; 
		this.note = note; 
		
	}
	
	/**
	 * Get command value
	 * @return type of command
	 */
	public CommandValue getCommand() {
		return cv;
	}
	
	/**
	 * Get String ownerId
	 * @return ID of owner who is responsible for this ticket
	 */
	public String getOwnerId() {
		return ownerId; 
	}
	
	/**
	 * get resolution code of this command as type ResolutionCode
	 * @return resolution code of command. Null if no active resolution code. 
	 */
	public ResolutionCode getResolutionCode() {
		return rc;
	}
	
	/**
	 * get note as string
	 * @return note attached to this command, as a string 
	 */
	public String getNote() {
		return note; 
	}
	
	/**
	 * get feedback code of this command as FeedbackCode
	 * @return feedback code this command is setting the code of the target ticket to. Null if no active feedback code. 
	 */
	public FeedbackCode getFeedbackCode() {
		return fc; 
	}
	
	/**
	 * get cancellation code of this command as CancellationCode
	 * @return cancellation code this command is setting the code of the target ticket to. Null if no active cancellation code. 
	 */
	public CancellationCode getCancellationCode() {
		return cc;
	}
}
