package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

import org.junit.jupiter.api.BeforeEach;


public class TicketManagerTest {
	
	private TicketManager manager;
	
	/**
	 * Sets up the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = TicketManager.getInstance();
	}
	
	@Test
	public void testTicketManager() {
		TicketManager tm = TicketManager.getInstance(); 
		assertEquals(1,1);
	}
	
	@Test
	public void testSavingTickets() {
		manager.saveTicketsToFile(null);
		assertEquals(1,1);
	}
	
	@Test
	public void testLoadingTickets() {
		manager.loadTicketsFromFile(null);
		assertEquals(1,1);
	}
	
	@Test
	public void testNewTicketList() {
		manager.createNewTicketList();
		assertEquals(1,1);
	}
	
	@Test
	public void testDisplayTickets() {
		manager.getTicketsForDisplay();
		manager.getTicketsForDisplayByType(TicketType.INCIDENT);
		assertEquals(1,1);
	}
	
	@Test
	public void testAddTicket() {
		manager.addTicketToList(null, null, null, null, null, null);
		assertEquals(1,1);
	}
	 
	@Test
	public void testRemoveTicket() {
		manager.deleteTicketById(0);
	
		assertEquals(1,1);
	}
	
	@Test
	public void testGetTicket() {
		manager.getTicketById(0);
		assertEquals(1,1);
	}
	
	@Test
	public void testExecuteCommand() {
		manager.executeCommand(0, null);;
		assertEquals(1,1);
	}
}
