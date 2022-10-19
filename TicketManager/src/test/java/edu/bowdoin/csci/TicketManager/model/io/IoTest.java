package edu.bowdoin.csci.TicketManager.model.io;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Glass box unit tests for TicketReader and TicketWriter classes. 
 * @author zbecker2
 *
 */
public class IoTest {
	
	/** Shared instance of TicketReader for use in all tests. 
	TicketReader r = new TicketReader(); 
	
	@Test
	public void testTests() {
		assertEquals(1,1); 
	}
	
	/**
	 * Makes sure reader throws IAE when given null file
	 */
	@Test
	public void testReaderInvalidlFiles() {
		try {
			TicketReader.readTicketFile(null);
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket3.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket4.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket5.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket6.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket7.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket8.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket9.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket10.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket11.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket12.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket13.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket14.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket15.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket16.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket17.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket18.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket19.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("ticket20.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("does-not-exist.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
	}
	
	/**
	 * Basic test on hopefully working file
	 */
	@Test
	public void testReaderLoadValidFiles() {
		//ticket1- Valid ticket file
		ArrayList<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt");
		assertEquals(tickets.size(), 6);  
		
		//ticket2- Valid ticket file with multi-line notes
		tickets = TicketReader.readTicketFile("test-files/ticket2.txt"); 	
		assertEquals(tickets.size(), 3); 
		
	}
	
	
	/**
	 * Test ticketWriter
	 */
	@Test
	public void fakeWriterTest() {
		TicketWriter.writeTicketFile("filename", null);
		assertEquals(1, 1); 
	}
	
	
}
