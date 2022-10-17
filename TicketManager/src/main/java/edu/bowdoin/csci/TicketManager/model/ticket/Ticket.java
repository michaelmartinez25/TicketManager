package edu.bowdoin.csci.TicketManager.model.ticket;

import java.util.ArrayList;

import edu.bowdoin.csci.TicketManager.model.command.Command;
import edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import edu.bowdoin.csci.TicketManager.model.command.Command.CancellationCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import edu.bowdoin.csci.TicketManager.model.command.Command.ResolutionCode;

/**
 * Ticket object represents a single filed ticket containing information about a specific issue/request.
 * @author zbecker2
 * @author mmartinez
 */
public class Ticket {
	
	/** String representation for ticket type request */
	public static final String TT_REQUEST = "Request"; 
	/** String representation for ticket type incident */
	public static final String TT_INCIDENT = "Incident"; 
	/** String representation for ticket category inquiry */
	public static final String C_INQUIRY = "Inquiry"; 
	/** String representation for ticket category software */
	public static final String C_SOFTWARE = "Software"; 
	/** String representation for ticket category hardware */
	public static final String C_HARDWARE = "Hardware";
	/** String representation for ticket category network */
	public static final String C_NETWORK = "Network"; 
	/** String representation for ticket category database */
	public static final String C_DATABASE = "Database"; 
	/** String representation for ticket priority urgent */
	public static final String P_URGENT = "Urgent"; 
	/** String representation for ticket priority high */
	public static final String P_HIGH = "High"; 
	/** String representation for ticket priority medium */
	public static final String P_MEDIUM = "Medium"; 
	/** String representation for ticket priority low */
	public static final String P_LOW = "Low"; 
	/** String representation for New state */ 
	public static final String NEW_NAME = "New"; 
	/** String representation for Working state */
	public static final String WORKING_NAME = "Working"; 
	/** String representation for Feedback state */ 
	public static final String FEEDBACK_NAME = "Feedback"; 
	/** String representation for Resolved state */ 
	public static final String RESOLVED_NAME = "Resolved"; 
	/** String representation for Closed state */ 
	public static final String CLOSED_NAME = "Closed"; 
	/** String representation for Canceled state */
	public static final String CANCELED_NAME = "Canceled"; 
	
	/** Static counter variable keeps track of the ID value that should be given to the next ticket created */
	private static int counter = 0; 
	/** Unique ticket ID assigned by system at ticket creation */ 
	private int ticketId;
	/** Subject of ticket */ 
	private String subject; 
	/** ID of caller who originated the ticket */ 
	private String caller; 
	/** ID of owner who is responsible for the ticket or " " (empty string) if none is assigned */ 
	private String owner; 
	/** Notes about the ticket */ 
	private ArrayList<String> notes; 
	/** Current state of the ticket. Of type TicketState */ 
	private TicketState state; 
	/** Final instance of NewState inner class */
	private final TicketState newState = new NewState();
	/** Final instance of WorkingState inner class */
	private final TicketState workingState = new WorkingState();
	/** Final instance of FeedbackState inner class */
	private final TicketState feedbackState = new FeedbackState();
	/** Final instance of ResolvedState inner class */
	private final TicketState resolvedState = new ResolvedState();
	/** Final instance of ClosedState inner class */
	private final TicketState closedState = new ClosedState();
	/** Final instance of CanceledState inner class */
	private final TicketState canceledState = new CanceledState();
	/** Current cancellation code of the ticket */ 
	private CancellationCode cancellationCode; 
	/** Current feedback code of the ticket */ 
	private FeedbackCode feedbackCode; 
	/** Current resolution code of the ticket */ 
	private ResolutionCode resolutionCode; 
	/** Current type of the ticket */ 
	private TicketType ticketType; 
	/** Current category of ticket */ 
	private Category category; 
	/** Current priority of the ticket */ 
	private Priority priority; 
	
	/** Options for ticket category */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }
	/** options for ticket priority */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW }
	/** options for ticket type */
	public enum TicketType { REQUEST, INCIDENT }
	
	/** Increase counter */ 
	public static void incrementCounter() {
		counter += 1; 
	}
	
	/** 
	 * set counter to number 
	 * @param num number to set counter to 
	 */
	public static void setCounter(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		counter = num; 
	}
	
	/** 
	 * Constructor for existing ticket being read from file
	 * 
	 * This constructor is intended to create a Ticket when reading input from a 
	 * ticket file (as per UC1). Additionally, the Ticket.counter should be 
	 * updated with Ticket.setCounter(id + 1) IF, the incoming id is greater than the 
	 * current value in Ticket.counter.
	 * 
	 * @throws IllegalArgumentException if any of the rules in UC1 are broken.
	 * 
	 * @param id Unique integer ID for this ticket
	 * @param state : String representing current state of this ticket
	 * @param ticketType String representing type of this ticket
	 * @param subject String representing subject of this ticket
	 * @param caller String identifying caller who originated the ticket
	 * @param category String identifying category of this ticket
	 * @param priority String determining what priority level this ticket has
	 * @param owner String representing owner responsible for this ticket
	 * @param code String determining what code this ticket has
	 * @param notes ArrayList of Strings with each note attached to this ticket. 
	 * */  
	public Ticket(int id, String state, String ticketType, String subject, String caller, String category, String priority, String owner, String code, ArrayList<String> notes) {
		//		if (type == null || subject == null || "".equals(subject) || caller == null || "".equals(caller) || category == null || priority == null || note == null || "".equals(note)) {

		if (id < 0 || state == null || "".equals(state) || ticketType == null || "".equals(ticketType) || subject == null || "".equals(subject) || caller == null || "".equals(caller) || category == null || "".equals(category) || priority == null || "".equals(priority) || code == null || "".equals(code) || notes == null || notes.isEmpty()) {
			throw new IllegalArgumentException(); 
		}
		
		this.ticketId = id; 
		
		if (id > counter) {
			setCounter(id + 1);
		}
		
		setState(state); 
		setTicketType(ticketType); 
		setSubject(subject); 
		setCaller(caller); 
		setCategory(category); 
		setPriority(priority);
		setOwner(owner); 
		setFeedbackCode(code); 
		setResolutionCode(code); 
		setCancellationCode(code);
		this.notes = notes; 
		
	}
		
	
	/** 
	 * Constructor for new ticket
	 * 
	 * Constructs a Ticket from the provided parameters. The ticketId is set to the 
	 * value stored in Ticket.counter. The counter is then incremented 
	 * using Ticket.incrementCounter(). The rest of the fields are initialized 
	 * to the parameter values, null, false, or an empty object type as 
	 * appropriate. The owner field should be initialized to an empty string (e.g., "").
	 * 
	 * @throws IllegalArgumentException is thrown if any of the parameters are null 
	 * or empty strings (if a String type), 
	 * 
	 * @param type Type of ticket
	 * @param subject String noting subject of this ticket 
	 * @param caller String identifying caller who originated the ticket
	 * @param category Category identifying what category of issue this ticket relates to
	 * @param priority Priority of ticket
	 * @param note String? Blank on spec. Guessing this should be note. 
	 * 
	 *  */ 
	public Ticket(TicketType type, String subject, String caller, Category category, Priority priority, String note) {
		
		if (type == null || subject == null || "".equals(subject) || caller == null || "".equals(caller) || category == null || priority == null || note == null || "".equals(note)) {
			throw new IllegalArgumentException();
		}
		
		this.ticketId = counter; 
		incrementCounter(); 
		
		setState(NEW_NAME); 
		this.ticketType = type; 
		setSubject(subject); 
		setCaller(caller); 
		this.category = category; 
		this.priority = priority; 
		setOwner(""); 
		setFeedbackCode(null); 
		setResolutionCode(null); 
		setCancellationCode(null); 
		ArrayList<String> notesList = new ArrayList<String>(); 
		notesList.add(note); 
		this.notes = notesList; 
		
	}
	
	/** 
	 * Get identifier of caller who originated the ticket
	 * @return callerID
	 */ 
	public String getCaller() {
		return caller; 
	}
	
	/**
	 *  Get cancellation code, if one exists. Return string representation. 
	 *  @return cancellationCode 
	 */
	public String getCancellationCode() {
		
		if (cancellationCode == CancellationCode.DUPLICATE) return Command.CC_DUPLICATE; 
		if (cancellationCode == CancellationCode.INAPPROPRIATE) return Command.CC_INAPPROPRIATE; 
		
		return null;
		
	}
	
	/**
	 *  Get ticket category
	 *  @return category
	 */ 
	public String getCategory() {
		
		if (category == Category.DATABASE) return C_DATABASE; 
		if (category == Category.HARDWARE) return C_HARDWARE; 
		if (category == Category.SOFTWARE) return C_SOFTWARE; 
		if (category == Category.INQUIRY) return C_INQUIRY; 
		if (category == Category.NETWORK) return C_NETWORK; 
		
		return null; 
	}
	
	/**
	 *  Get feedback code, if one exists 
	 *  @return feedbackCode
	 */ 
	public String getFeedbackCode() {
		
		if (feedbackCode == FeedbackCode.AWAITING_CALLER) return Command.F_CALLER; 
		if (feedbackCode == FeedbackCode.AWAITING_CHANGE) return Command.F_CHANGE; 
		if (feedbackCode == FeedbackCode.AWAITING_PROVIDER) return Command.F_PROVIDER; 
		
		return null; 
	}
	
	/** 
	 * Get set of notes for this ticket, represented as a string. 
	 * Each individual note occurs on its own line, with one newline character following. 
	 * @return notes as string 
	 */ 
	public String getNotes() {
		
		String output = ""; 
				
		for (int i = 0; i < notes.size(); i++) {
			output += "-" + notes.get(i);
			output += System.lineSeparator(); 
			
			
		}
		
		return output; 
	}
	
	/** 
	 * Get id of owner who is responsible for this ticket 
	 * @return ownerId
	 */
	public String getOwner() {
		return owner;
	}
	
	/** 
	 * Get priority of ticket, represented as string
	 * @return priority
	 */ 
	public String getPriority() {
		
		if (priority == Priority.URGENT) return P_URGENT; 
		if (priority == Priority.HIGH) return P_HIGH; 
		if (priority == Priority.MEDIUM) return P_MEDIUM; 
		if (priority == Priority.LOW) return P_LOW; 
		
		return null; 
	}
	
	/**
	 *  Get resolution code if one exists 
	 * @return resolutionCode
	 */ 
	public String getResolutionCode() {
		
		if (resolutionCode == ResolutionCode.CALLER_CLOSED) return Command.RC_CALLER_CLOSED; 
		if (resolutionCode == ResolutionCode.COMPLETED) return Command.RC_COMPLETED; 
		if (resolutionCode == ResolutionCode.NOT_COMPLETED) return Command.RC_NOT_COMPLETED; 
		if (resolutionCode == ResolutionCode.SOLVED) return Command.RC_SOLVED; 
		if (resolutionCode == ResolutionCode.NOT_SOLVED) return Command.RC_NOT_SOLVED; 
		if (resolutionCode == ResolutionCode.WORKAROUND) return Command.RC_WORKAROUND; 
		
		return null;
	}
	
	/**
	 *  Get current state of this ticket, returned as string 
	 *  @return state as string 
	 */ 
	public String getState() {
		
		if (state == newState) return NEW_NAME; 
		if (state == workingState) return WORKING_NAME; 
		if (state == feedbackState) return FEEDBACK_NAME; 
		if (state == resolvedState) return RESOLVED_NAME; 
		if (state == canceledState) return CANCELED_NAME; 
		if (state == closedState) return CLOSED_NAME; 
 		
		return null;
	}
	
	/** 
	 * Get subject of this ticket 
	 * @return ticketSubject
	 */ 
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Get id of this ticket
	 * @return ticketID
	 */
	public int getTicketId() {
		return ticketId; 
	}
	
	/** 
	 * Get type of ticket 
	 * @return ticketType
	 */ 
	public TicketType getTicketType() {
		return ticketType; 
	}
	
	/**
	 * Get ticket type represented as a string 
	 * @return ticketType
	 */
	public String getTicketTypeString() {
		
		if (ticketType == TicketType.INCIDENT) return TT_INCIDENT; 
		if (ticketType == TicketType.REQUEST) return TT_REQUEST; 
		
		throw new IllegalArgumentException();
	}
	
	/**
	 * Set and validate id of caller who originated the ticket
	 * @param callerId identifier for caller
	 */ 
	private void setCaller(String callerId) {
		if (callerId == null || "".equals(callerId)) {
			throw new IllegalArgumentException(); 
		}
		else {
			this.caller = callerId; 
		}
	}
	
	/**
	 * Set cancellation code based on CC name string
	 * @param cc desired cancellation code
	 */ 
	private void setCancellationCode(String cc) {
		cancellationCode = null; 
		if (cc == Command.CC_DUPLICATE) cancellationCode = CancellationCode.DUPLICATE; 
		if (cc == Command.CC_INAPPROPRIATE) cancellationCode = CancellationCode.INAPPROPRIATE; 
		
		if (state == canceledState && !(cancellationCode == CancellationCode.DUPLICATE) || cancellationCode == CancellationCode.INAPPROPRIATE) {
			throw new IllegalArgumentException("The ticket must have a cancellation code of either \"Duplicate\" or \"Inappropriate\", if in the \"Canceled\" state.");
		}
	}
	
	/**
	 * Set category based on category name string 
	 * @param category of ticket
	 */ 
	private void setCategory(String category) {
		
		this.category = null;
		if (category == C_DATABASE) {
			this.category = Category.DATABASE; 
			return;
		}
		if (category == C_NETWORK) {
			this.category = Category.NETWORK; 
			return;
		}
		if (category == C_SOFTWARE) {
			this.category = Category.SOFTWARE; 
			return;
		}
		if (category == C_HARDWARE) {
			this.category = Category.HARDWARE; 
			return;
		}
		if (category == C_INQUIRY) {
			this.category = Category.INQUIRY; 
			return;
		}
		
		throw new IllegalArgumentException();
	}
	
	/** 
	 * Set and validate id of owner who is responsible for ticket
	 * @param id string identifying owner of ticket
	 */ 
	private void setOwner(String id) {
		if (id == null || "".equals(id)) {
			if (state != canceledState && state != newState) {
				throw new IllegalArgumentException("The ticket must have an owner if the state is \"Working\", \"Feedback\", \"Resolved\", or \"Closed\""); 
			}
			this.owner = ""; 
		}
		else this.owner = id; 
	}
	
	/**
	 * Set feedback code based on fc name string
	 * @param fc feedbackCode to be set
	 */ 
	private void setFeedbackCode(String fc) {
		this.feedbackCode = null;
		
		if (fc == Command.F_CALLER) {
			this.feedbackCode = FeedbackCode.AWAITING_CALLER;
			return;
		}
		if (fc == Command.F_CHANGE) {
			this.feedbackCode = FeedbackCode.AWAITING_CHANGE;
			return;
		}
		if (fc == Command.F_PROVIDER) {
			this.feedbackCode = FeedbackCode.AWAITING_PROVIDER; 
			return;
		}
		
		if (state == feedbackState && feedbackCode == null) {
			throw new IllegalArgumentException("The ticket must have a feedback code of either \"Awaiting Caller\", \"Awaiting Change\", or \"Awaiting Provider\" when in the \"Feedback\" state."); 
		}
	}
	
	/**
	 * Set priority of this ticket based on string
	 * @param priority of ticket as string 
	 */ 
	private void setPriority(String priority) {
		
		if (priority == P_URGENT) {
			this.priority = Priority.URGENT; 
			return;
		}
		if (priority == P_HIGH) {
			this.priority = Priority.HIGH; 
			return;
		}
		if (priority == P_MEDIUM) {
			this.priority = Priority.MEDIUM; 
			return;
		}
		if (priority == P_LOW) {
			this.priority = Priority.LOW;
			return;
		}
		
		throw new IllegalArgumentException();
	}
	
	/**
	 * Set and validate resolution code
	 * @param rc name of resolutionCode to be set, as string
	 */ 
	private void setResolutionCode(String rc) {
		
		this.resolutionCode = null;
		
		if (rc == Command.RC_CALLER_CLOSED) this.resolutionCode = ResolutionCode.CALLER_CLOSED; 
		if (rc == Command.RC_COMPLETED) this.resolutionCode = ResolutionCode.COMPLETED;
		if (rc == Command.RC_NOT_COMPLETED) this.resolutionCode = ResolutionCode.NOT_COMPLETED; 
		if (rc == Command.RC_SOLVED) this.resolutionCode = ResolutionCode.SOLVED; 
		if (rc == Command.RC_NOT_COMPLETED) this.resolutionCode = ResolutionCode.NOT_COMPLETED; 
		if (rc == Command.RC_WORKAROUND) this.resolutionCode = ResolutionCode.WORKAROUND; 
		
		if (state == resolvedState || state == closedState) {
			if (!(resolutionCode == ResolutionCode.COMPLETED || resolutionCode == ResolutionCode.NOT_COMPLETED || resolutionCode == ResolutionCode.SOLVED || resolutionCode == ResolutionCode.WORKAROUND || resolutionCode == ResolutionCode.NOT_SOLVED || resolutionCode == ResolutionCode.CALLER_CLOSED)) {
				throw new IllegalArgumentException("The ticket must have a resolution code of either \"Completed\", \"Not Completed\", \"Solved\", \"Workaround\", \"Not Solved\", or \"Caller Closed\" in the \"Resolved\" or \"Closed\" states.");
			}
			
			if (ticketType == TicketType.REQUEST && !(resolutionCode == ResolutionCode.COMPLETED || resolutionCode == ResolutionCode.NOT_COMPLETED || resolutionCode == ResolutionCode.CALLER_CLOSED)) {
				throw new IllegalArgumentException("If the ticket is a \"Request\", the code can only be \"Completed\", \"Not Completed\", or \"Caller Closed\". "); 
			}
			
			if (ticketType == TicketType.INCIDENT && !(resolutionCode == ResolutionCode.SOLVED || resolutionCode == ResolutionCode.WORKAROUND || resolutionCode == ResolutionCode.NOT_SOLVED || resolutionCode == ResolutionCode.CALLER_CLOSED)) {
				throw new IllegalArgumentException("If the ticket is an \"Incident\", the code can only be \"Solved\", \"Workaround\", \"Not Solved\", or \"Called Closed\".");
			}
		}
		
		
	}
	
	/**
	 * Set and validate current ticket state
	 * @param s name of desired ticket state, as a string */ 
	private void setState(String s) {
		
		if (s == NEW_NAME) {
			this.state = newState; 
			return;
		}
		if (s == WORKING_NAME) {
			this.state = workingState; 
			return;
		}
		if (s == FEEDBACK_NAME) {
			this.state = feedbackState;
			return;
		}
		if (s == CANCELED_NAME) {
			this.state = canceledState; 
			return;
		}
		if (s == CLOSED_NAME) {
			this.state = closedState; 
			return;
		}
		if (s == RESOLVED_NAME) {
			this.state = resolvedState; 
			return;
		}

		throw new IllegalArgumentException();
	}
	
	/**
	 * Set and validate ticket subject
	 * @param s subject of ticket
	 */ 
	private void setSubject(String s) {
		if (s == null || "".equals(s)) {
			throw new IllegalArgumentException(); 
		}
		
		this.subject = s;
	}
	
	/**
	 * Set and validate ticket type
	 * @param tt name of ticket type, as string
	 */ 
	private void setTicketType(String tt) {
		
		if (tt == TT_INCIDENT) {
			ticketType = TicketType.INCIDENT; 
			return;
		}
		if (tt == TT_REQUEST) {
			ticketType = TicketType.REQUEST; 
			return;
		}
		
		throw new IllegalArgumentException();
	}
	
	/**
	 * Each field of the ticket is printed with a label, then the notes are printed with one on each line. 
	 * @return string representation of this ticket object
	 */
	@Override
	public String toString() {
		
		String output = ""; 
		
		output += "Ticket ID: " + getTicketId() + System.lineSeparator();
		output += "Subject: " + getSubject() + System.lineSeparator(); 
		output += "Category: " + getCategory() + System.lineSeparator(); 
		output += "Priority: " + getPriority() + System.lineSeparator(); 
		output += "Current state: " + getState() + System.lineSeparator(); 
		output += "Type: " + getTicketTypeString() + System.lineSeparator(); 
		output += "Caller: " + getCaller() + System.lineSeparator(); 
		output += "Owner: " + getOwner() + System.lineSeparator(); 
		
		if (resolutionCode != null) output += "Resolution code: " + getResolutionCode() + System.lineSeparator();
		if (feedbackCode != null) output += "Feedback code: " + getFeedbackCode() + System.lineSeparator(); 
		if (cancellationCode != null) output += "Cancellation code: " + getCancellationCode() + System.lineSeparator();
		
		output += "Notes: " + System.lineSeparator() + getNotes(); 
		
		return output; 
	}
	
	/**
	 * updates the state of the ticket given a Command object
	 * 
	 * @param c command instructing ticket what action to take
	 */
	public void update(Command c) {
		String name = state.getStateName();
		
		if (name.equals(newState.getStateName())) {
			newState.updateState(c);
		}
		
		if (name.equals(workingState.getStateName())) {
			workingState.updateState(c);
		}
		
		if (name.equals(feedbackState.getStateName())) {
			feedbackState.updateState(c);
		}
		
		if (name.equals(resolvedState.getStateName())) {
			resolvedState.updateState(c);
		}
		
		if (name.equals(closedState.getStateName())) {
			closedState.updateState(c);
		}
		
		if (name.equals(canceledState.getStateName())) {
			canceledState.updateState(c);
		}
	}
	
	/** NewState inner class */
	private class NewState implements TicketState {
		
		/** Constructor method */
		private NewState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return NEW_NAME;
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException();
			}
			
			CommandValue cv = command.getCommand();
			
			if (!cv.equals(CommandValue.PROCESS) && !cv.equals(CommandValue.CANCEL)) {
				throw new UnsupportedOperationException();
			}
			
			// Transition to WorkingState
			if (cv.equals(CommandValue.PROCESS)) {
				if (command.getOwnerId() == null || command.getOwnerId() == "") {
					throw new UnsupportedOperationException();
				}
				owner = command.getOwnerId();
				notes.add(command.getNote());
				state = workingState;
			}
			
			if (cv.equals(CommandValue.CANCEL)) {
				CancellationCode newCancellationCode = command.getCancellationCode();
				if (command.getCancellationCode() == null) {
					throw new UnsupportedOperationException();
				}
				
				cancellationCode = newCancellationCode;
				notes.add(command.getNote());
				state = canceledState;
			}
			
		}
	}
	
	/** WorkingState inner class */
	private class WorkingState implements TicketState {
		
		/** Constructor method */
		private WorkingState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return WORKING_NAME;
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException();
			}
			
			CommandValue cv = command.getCommand();
			
			if (!cv.equals(CommandValue.FEEDBACK) && !cv.equals(CommandValue.RESOLVE) && !cv.equals(CommandValue.CANCEL)) {
				throw new UnsupportedOperationException();
			}
			
			if (cv.equals(CommandValue.FEEDBACK)) {
				FeedbackCode newFeedbackCode = command.getFeedbackCode();
				if (command.getFeedbackCode() == null) {
					throw new UnsupportedOperationException();
				}
				feedbackCode = newFeedbackCode;
				notes.add(command.getNote());
				state = feedbackState;
			}
			
			if (cv.equals(CommandValue.RESOLVE)) {
				ResolutionCode newResolutionCode =  command.getResolutionCode();
				if (command.getResolutionCode() == null) {
					throw new UnsupportedOperationException();
				}
				resolutionCode = newResolutionCode;
				notes.add(command.getNote());
				state = resolvedState;
			}
			
			if (cv.equals(CommandValue.CANCEL)) {
				CancellationCode newCancellationCode = command.getCancellationCode();
				if (command.getCancellationCode() == null) {
					throw new UnsupportedOperationException();
				}
				
				cancellationCode = newCancellationCode;
				notes.add(command.getNote());
				state = canceledState;
			}
			
		}
	}
	
	/** FeedbackState inner class */
	private class FeedbackState implements TicketState {
		
		/** Constructor method */
		private FeedbackState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return FEEDBACK_NAME;
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException();
			}
			
			CommandValue cv = command.getCommand();
			
			if (!cv.equals(CommandValue.REOPEN) && !cv.equals(CommandValue.RESOLVE) && !cv.equals(CommandValue.CANCEL)) {
				throw new UnsupportedOperationException();
			}
			
			if (cv.equals(CommandValue.REOPEN)) {
				if (command.getOwnerId() == null || command.getOwnerId() == "") {
					throw new UnsupportedOperationException();
				}
				owner = command.getOwnerId();
				notes.add(command.getNote());
				state = workingState;
			}
			
			if (cv.equals(CommandValue.RESOLVE)) {
				ResolutionCode newResolutionCode =  command.getResolutionCode();
				if (command.getResolutionCode() == null) {
					throw new UnsupportedOperationException();
				}
				resolutionCode = newResolutionCode;
				notes.add(command.getNote());
				state = resolvedState;
			}
			
			if (cv.equals(CommandValue.CANCEL)) {
				CancellationCode newCancellationCode = command.getCancellationCode();
				if (command.getCancellationCode() == null) {
					throw new UnsupportedOperationException();
				}
				
				cancellationCode = newCancellationCode;
				notes.add(command.getNote());
				state = canceledState;
			}
		}
	}
	
	/** ResolvedState inner class */
	private class ResolvedState implements TicketState {
		
		/** Constructor method */
		private ResolvedState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return RESOLVED_NAME;
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException();
			}
			
			CommandValue cv = command.getCommand();
			
			if (!cv.equals(CommandValue.FEEDBACK) && !cv.equals(CommandValue.REOPEN) && !cv.equals(CommandValue.CONFIRM) && !cv.equals(CommandValue.CANCEL)) {
				throw new UnsupportedOperationException();
			}
			
			if (cv.equals(CommandValue.FEEDBACK)) {
				FeedbackCode newFeedbackCode = command.getFeedbackCode();
				if (command.getFeedbackCode() == null) {
					throw new UnsupportedOperationException();
				}
				feedbackCode = newFeedbackCode;
				notes.add(command.getNote());
				state = feedbackState;
			}
			
			if (cv.equals(CommandValue.REOPEN)) {
				if (command.getOwnerId() == null || command.getOwnerId() == "") {
					throw new UnsupportedOperationException();
				}
				owner = command.getOwnerId();
				notes.add(command.getNote());
				state = workingState;
			}
			
			if (cv.equals(CommandValue.CONFIRM)) {
				notes.add(command.getNote());
				state = closedState;
			}
			
			if (cv.equals(CommandValue.CANCEL)) {
				CancellationCode newCancellationCode = command.getCancellationCode();
				if (command.getCancellationCode() == null) {
					throw new UnsupportedOperationException();
				}
				
				cancellationCode = newCancellationCode;
				notes.add(command.getNote());
				state = canceledState;
			}
		}
	}
	
	/** ClosedState inner class */
	private class ClosedState implements TicketState {
		
		/** Constructor method */
		private ClosedState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return CLOSED_NAME;
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException();
			}
			
			CommandValue cv = command.getCommand();
			
			if (!cv.equals(CommandValue.REOPEN)) {
				throw new UnsupportedOperationException();
			}
			
			if (cv.equals(CommandValue.REOPEN)) {
				if (command.getOwnerId() == null || command.getOwnerId() == "") {
					throw new UnsupportedOperationException();
				}
				owner = command.getOwnerId();
				notes.add(command.getNote());
				state = workingState;
			}
		}
	}
	
	/** CanceledState inner class */
	private class CanceledState implements TicketState {
		
		/** Constructor method */
		private CanceledState() {
			// Should be left blank
		}
		
		/**
		 * Gets state name
		 * @return state name
		 */
		public String getStateName() {
			return CANCELED_NAME; 
		}
		
		/**
		 * Updates state based on given command
		 * @param command user command to execute
		 */
		public void updateState(Command command) {
			throw new UnsupportedOperationException();
		}
	}
 }
