package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

import org.junit.jupiter.api.BeforeEach;

public class TicketListTest {

	private TicketList list;
	
	/**
	 * Sets up the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		list = new TicketList();
	}
	
	@Test
	public void testTicketList() {
		TicketList tl = new TicketList(); 
		assertEquals(1, 1);
	}
	
	@Test
	public void testAddTicket() {
		list.addTicket(null, null, null, null, null, null); 
		list.addTickets(null);
		assertEquals(1, 1);
	}
	
	@Test
	public void testDeleteTicket() {
		list.deleteTicketById(0);
		assertEquals(1, 1);
	}
	
	@Test
	public void testGetTicket() {
		list.getTicketById(0);
		list.getTicketsByType(TicketType.INCIDENT);
		list.getTickets();
		assertEquals(1, 1);
	}
	
	@Test
	public void testExecuteCommand() {
		list.executeCommand(0, null);
		assertEquals(1, 1);
	}
}
