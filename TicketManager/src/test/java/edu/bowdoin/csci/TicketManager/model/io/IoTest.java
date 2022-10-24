package edu.bowdoin.csci.TicketManager.model.io;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Glass box unit tests for TicketReader and TicketWriter classes.  
 * @author Zeb Becker
 *
 */
public class IoTest { 
	
	/**
	 * Makes sure reader throws IAE when given files that should not load. 
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
	 * Basic check to confirm that valid ticket files are read successfully
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
	 * Ensure valid files are written successfully.
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
	 * Ensure that null parameters throw IAE. 
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
			fail("Null filename should throw IAE");
		}
		catch (IllegalArgumentException iae) {
			//exception expected; carry on. 
		}
	}
}
