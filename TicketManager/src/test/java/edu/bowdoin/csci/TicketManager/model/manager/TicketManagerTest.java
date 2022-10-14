package edu.bowdoin.csci.TicketManager.model.manager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
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
		assertEquals(1,1);
	}
	
	@Test
	public void testSavingTickets() {
		assertEquals(1,1);
	}
	
	@Test
	public void testLoadingTickets() {
		assertEquals(1,1);
	}
	
	@Test
	public void testNewTicketList() {
		assertEquals(1,1);
	}
	
	@Test
	public void testDisplayTickets() {
		assertEquals(1,1);
	}
	
	@Test
	public void testAddTicket() {
		assertEquals(1,1);
	}
	 
	@Test
	public void testRemoveTicket() {
		assertEquals(1,1);
	}
	
	@Test
	public void testGetTicket() {
		assertEquals(1,1);
	}
	
	@Test
	public void testExecuteCommand() {
		assertEquals(1,1);
	}
}
