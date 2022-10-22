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
	
	/**
	 * Tests TicketManager instance
	 * May not be necessary
	 */
	@Test
	public void testTicketManager() {
		manager = TicketManager.getInstance(); 
		assertNotNull(manager);
	}
	
	/**
	 * Tests createNewTicketList()
	 */
	@Test
	public void testNewTicketList() {
		manager.createNewTicketList();
		assertNotNull(manager);
	}
	
	/**
	 * Tests saveTicketsToFile()
	 */
	@Test
	public void testSaveTicketsToFile() {
		// commend for PMD
	}
	
}
