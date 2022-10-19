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
			TicketReader.readTicketFile("test-files/ticket3.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket4.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket5.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket6.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket7.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket8.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket9.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket10.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket11.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket12.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket13.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket14.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket15.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket16.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket17.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket18.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket19.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/ticket20.txt");
			fail("Should throw IAE on invalid file."); 
		}
		catch (IllegalArgumentException iae) {
			//Exception expected: carry on
		}
		try {
			TicketReader.readTicketFile("test-files/does-not-exist.txt");
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
	 * test writer with valid files
	 */
	@Test
	public void testWriterValidFile() {
		
		List<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt"); 
		TicketWriter.writeTicketFile("test-files/testfile.txt", tickets); 
		List<Ticket> fromFile = TicketReader.readTicketFile("test-files/testfile.txt"); 
		assertEquals(tickets.size(), fromFile.size()); 
		
		tickets = TicketReader.readTicketFile("test-files/ticket2.txt"); 
		TicketWriter.writeTicketFile("test-files/testfile.txt", tickets); 
		fromFile = TicketReader.readTicketFile("test-files/testfile.txt"); 
		assertEquals(tickets.size(), fromFile.size()); 
		
		
		
	}
	
	/**
	 * Test ticketWriter on invalid files
	 */
	@Test
	public void testWriterInvalidFiles() {
		try {
			TicketWriter.writeTicketFile("test-files/testfile.txt", null);
			fail("Null ticket List should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//exception expected; carry on. 
		}
		try {
			List<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt"); 
			TicketWriter.writeTicketFile(null, tickets); 
		}
		catch (IllegalArgumentException iae) {
			//exception expected; carry on. 
		}
		
		/** 
		 * Not actually sure if it is not allowed to write to new file
		 * 
		try {
			List<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt"); 
			TicketWriter.writeTicketFile("test-files/this_file_does_not_exist.txt", tickets); 
			fail("Non-existent output file should throw IAE"); 
		}
		catch (IllegalArgumentException iae) {
			//exception expected; carry on.
		}
		*/
		
	}
	
	
}
