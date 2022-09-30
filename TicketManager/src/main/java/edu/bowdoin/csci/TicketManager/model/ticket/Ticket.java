package edu.bowdoin.csci.TicketManager.model.ticket;

import java.util.ArrayList;

/**
 * Ticket object represents a single filed ticket containing information about a specific issue/request.
 * @author zbecker2
 *
 */
public class Ticket {
	
	/* Indicates if ticket is of type request */
	public static final String TT_REQUEST; 
	/* Indicates if ticket is of type incident */
	public static final String TT_INCIDENT; 
	/* Indicates if ticket subject is inquiry */
	public static final String C_INQUIRY; 
	/* Indicates if ticket subject is software */
	public static final String C_SOFTWARE; 
	/* Indicates if ticket subject is hardware */
	public static final String C_HARDWARE;
	/* Indicates if ticket subject is network */
	public static final String C_NETWORK; 
	/* Indicates if ticket subject is database */
	public static final String C_DATABSE; 
	/* Indicates if ticket priority is urgent */
	public static final String P_URGENT; 
	/* Indicates if ticket priority is high */
	public static final String P_HIGH; 
	/* Indicates if ticket priority is medium */
	public static final String P_MEDIUM; 
	/* Indicates if ticket priority is low */
	public static final String P_LOW; 
	/* Name of New state */ 
	public static final String NEW_NAME; 
	/* Name of Working state */
	public static final String WORKING_NAME; 
	/* Name of Feedback state */ 
	public static final String FEEDBACK_NAME; 
	/* Name of Resolved state */ 
	public static final String RESOLVED_NAME; 
	/* Name of Closed state */ 
	public static final String CLOSED_NAME; 
	/* Name of Canceled state */
	public static final String CANCELED_NAME; 
	/* counter variable */
	private static int counter; 
	/* Unique ticket ID assigned by system at ticket creation */ 
	private int ticketId;
	/* Subject of ticket */ 
	private String subject; 
	/* ID of caller who originated the ticket */ 
	private String caller; 
	/* ID of owner who is responsible for the ticket */ 
	private String owner; 
	/* Notes about the ticket */ 
	private ArrayList<String> notes; 
	/* Current state of the ticket */ 
	private TicketState state; 
	/* Current cancellation code of the ticket */ 
	private CancellationCode cancellationCode; 
	/* Current feedback code of the ticket */ 
	private FeedbackCode feedbackCode; 
	/* Current resolution code of the ticket */ 
	private ResolutionCode resolutionCode; 
	/* Current type of the ticket */ 
	private TicketType ticketType; 
	/* Current categoruy of ticket */ 
	private Category category; 
	/* Current priority of the ticket */ 
	private Priority priority; 
	
	
	
	/* Increase counter */ 
	public static void incrementCounter() {
		
	}
	
	/* set counter to number */
	public static void setCounter(int num) {
		
	}
	
	/* constructor */ 
	public Ticket(int i, String a, String b, String c, String d, String e, String f, String g, String h, ArrayList<String> j) {
		
	}
	
	/* alternate constructor */ 
	public Ticket(TicketType t, String a, String b, Category c, Priority p, String c) {
		
	}
	
	/* Get identifier of caller who originated the ticket */ 
	public String getCaller() {
		return caller; 
	}
	
	/* Get cancellation code, if one exists */
	public String getCancellationCode() {
		return ""; 
	}
	
	/* Get ticket category */ 
	public String getCategory() {
		return ""; 
	}
	
	/* Get feedback code, if one exists */ 
	public String getFeedbackCode() {
		return ""; 
	}
	
	/* Get set of notes for this ticket, represented as a string */ 
	public String getNotes() {
		return ""; 
	}
	
	/* Get id of owner who is responsible for this ticket */
	public String getOwner() {
		return "";
	}
	
	/* Get priority of ticket */ 
	public String getPriority() {
		return ""; 
	}
	
	/* Get resolution code if one exists */ 
	public String getResolutionCode() {
		return "";
	}
	
	/* Get current state of this ticket, returned as string */ 
	public String getState() {
		return "";
	}
	
	/* Get subject of this ticket */ 
	public String getSubject() {
		return "";
	}
	
	/* Get id of this ticket */
	public int getTicketId() {
		return 0; 
	}
	
	/* Get type of ticket */ 
	public TicketType getTicketType() {
		return null; 
	}
	
	/* Get ticket type represented as a string */
	public String getTicketTypeString() {
		return "";
	}
	
	/* Set and validate id of caller who originated the ticket */ 
	private void setCaller(String s) {
	}
	
	/* Set and validate cancellation code */ 
	private void setCancellationCode(String s) {
	}
	
	/* Set and validate category */ 
	private void setCategory(String s) {
		 
	}
	
	/* Set and validate id of owner who is responsible for ticket */ 
	private void setOwner(String s) {
		
	}
	
	/* Set and validate feedback code */ 
	private void setFeedbackCode(String s) {
		
	}
	
	/* Set and validate priority of ticket */ 
	private void setPriority(String s) {
		
	}
	
	/* Set and validate resolution code */ 
	private void setResolutionCode(String s) {
		
	}
	
	/* Set and validate current ticket state */ 
	private void setState(String s) {
		
	}
	
	/* Set and validate ticket subject */ 
	private void setSubject(String s) {
		
	}
	
	/* Set and validate ticket type */ 
	private void setTicketType(String s) {
		
	}
	
	/* return string representation of this ticket object */
	@Override
	public String toString() {
		return ""; 
	}
	
	public void update(Command c) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
 }
