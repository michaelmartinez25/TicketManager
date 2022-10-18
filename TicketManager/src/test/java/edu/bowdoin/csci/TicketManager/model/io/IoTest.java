package edu.bowdoin.csci.TicketManager.model.io;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;


public class IoTest {
	
	TicketReader r = new TicketReader(); 
	
	@Test
	public void testTests() {
		assertEquals(1,1); 
	}
	
	/**
	 * Makes sure reader throws IAE when given null file
	 */
	@Test
	public void testReaderNullFile() {
		try {
			r.readTicketFile(null);
			fail("Should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry om
		}
	}
	
	/**
	 * Basic test on hopefully working file
	 */
	@Test
	public void testReaderLoadValidFile() {
		ArrayList<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt");
		assertEquals(tickets.size(), 6);  
	}
	
	@Test
	public void fakeReaderTest() {
		TicketReader r = new TicketReader();
		assertEquals(r.readTicketFile("file name"), null); 
	}
	
	@Test
	public void fakeWriterTest() {
		TicketWriter w = new TicketWriter();
		w.writeTicketFile("filename", null);
		assertEquals(1, 1); 
	}
	
	
}
