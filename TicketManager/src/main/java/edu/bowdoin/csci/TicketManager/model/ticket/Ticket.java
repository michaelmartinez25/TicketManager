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
	public static final String P_URGENT = "Urgen"; 
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
		
	}
	
	/** 
	 * set counter to number 
	 * @param num number to set counter to 
	 */
	public static void setCounter(int num) {
		
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
		ticketId = id; 
		//use setter methods for validation 
		//pass all work onto other constructor if possible
		
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
		
		
		
	}
	
	/** 
	 * Get identifier of caller who originated the ticket
	 * @return callerID
	 */ 
	public String getCaller() {
		return caller; 
	}
	
	/**
	 *  Get cancellation code, if one exists 
	 *  @return cancellationCode 
	 */
	public String getCancellationCode() {
		return ""; 
	}
	
	/**
	 *  Get ticket category
	 *  @return category
	 */ 
	public String getCategory() {
		return ""; 
	}
	
	/**
	 *  Get feedback code, if one exists 
	 *  @return feedbackCode
	 */ 
	public String getFeedbackCode() {
		return ""; 
	}
	
	/** 
	 * Get set of notes for this ticket, represented as a string 
	 * @return notes as string 
	 */ 
	public String getNotes() {
		return ""; 
	}
	
	/** 
	 * Get id of owner who is responsible for this ticket 
	 * @return ownerId
	 */
	public String getOwner() {
		return "";
	}
	
	/** 
	 * Get priority of ticket 
	 * @return priority
	 */ 
	public String getPriority() {
		return ""; 
	}
	
	/**
	 *  Get resolution code if one exists 
	 * @return resolutionCode
	 */ 
	public String getResolutionCode() {
		return "";
	}
	
	/**
	 *  Get current state of this ticket, returned as string 
	 *  @return state as string 
	 */ 
	public String getState() {
		return "";
	}
	
	/** 
	 * Get subject of this ticket 
	 * @return ticketSubject
	 */ 
	public String getSubject() {
		return "";
	}
	
	/**
	 * Get id of this ticket
	 * @return ticketID
	 */
	public int getTicketId() {
		return 0; 
	}
	
	/** 
	 * Get type of ticket 
	 * @return ticketType
	 */ 
	public TicketType getTicketType() {
		return null; 
	}
	
	/**
	 * Get ticket type represented as a string 
	 * @return ticketType
	 */
	public String getTicketTypeString() {
		return "";
	}
	
	/**
	 * Set and validate id of caller who originated the ticket
	 * @param callerId
	 */ 
	private void setCaller(String s) {
	}
	
	/**
	 * Set and validate cancellation code 
	 * @param desired cancellation code
	 */ 
	private void setCancellationCode(String s) {
	}
	
	/**
	 * Set and validate category 
	 * @param category of ticket
	 */ 
	private void setCategory(String s) {
		 
	}
	
	/** 
	 * Set and validate id of owner who is responsible for ticket
	 * @param ownerId
	 */ 
	private void setOwner(String s) {
		
	}
	
	/**
	 * Set and validate feedback code 
	 * @param feedbackCode to be set
	 */ 
	private void setFeedbackCode(String s) {
		
	}
	
	/**
	 * Set and validate priority of ticket 
	 * @param priority of ticket as string 
	 */ 
	private void setPriority(String s) {
		
	}
	
	/**
	 * Set and validate resolution code
	 * @param resolutionCode to be set
	 */ 
	private void setResolutionCode(String s) {
		
	}
	
	/**
	 * Set and validate current ticket state
	 * @param name of desired ticket state, as a string */ 
	private void setState(String s) {
		
	}
	
	/**
	 * Set and validate ticket subject
	 * @param subject of ticket
	 */ 
	private void setSubject(String s) {
		
	}
	
	/**
	 * Set and validate ticket type
	 * @param type of subject to set ticket as
	 */ 
	private void setTicketType(String s) {
		
	}
	
	/**
	 * return string representation of this ticket object
	 * @return string representation of this ticket object
	 */
	@Override
	public String toString() {
		return ""; 
	}
	
	/**
	 * updates the state of the ticket given a Command object
	 * @param command instructing ticket what action to take\
	 */
	public void update(Command c) {
		
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
			
			if (!cv.equals(CommandValue.PROCESS) || !cv.equals(CommandValue.CANCEL)) {
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
			
			if (!cv.equals(CommandValue.FEEDBACK) || !cv.equals(CommandValue.RESOLVE) || !cv.equals(CommandValue.CANCEL)) {
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
			
			if (!cv.equals(CommandValue.REOPEN) || !cv.equals(CommandValue.RESOLVE) || !cv.equals(CommandValue.CANCEL)) {
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
			
			if (!cv.equals(CommandValue.FEEDBACK) || !cv.equals(CommandValue.REOPEN) || !cv.equals(CommandValue.CONFIRM) || !cv.equals(CommandValue.CANCEL)) {
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
			
		}
	}
 }
