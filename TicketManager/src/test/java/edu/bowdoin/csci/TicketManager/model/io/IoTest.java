package edu.bowdoin.csci.TicketManager.model.io;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;


public class IoTest {
	@Test
	public void testTests() {
		assertEquals(1,1); 
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
