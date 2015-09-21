package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {
	
	@Test 
	public void InitialTest() {
		Item item = new Item("Book");
		assertEquals(item.getName(), "Book");
		assertEquals(item.getQuantity(), 1); 
		assertEquals(item.getBorrower(), "N/A");
		assertEquals(item.getDueDate(), "N/A");
		assertEquals(item.getStatus(), "Pending"); 
		
	}
	
	@Test 
	public void ChangingTest() {
		Item item = new Item("Book");
		assertEquals(item.getName(), "Book");
		item.setName("Jabberwocky"); 
		assertEquals(item.getName(), "Jabberwocky");
		item.setBorrower("John Smith"); 
		assertEquals(item.getBorrower(), "John Smith"); 
		item.setDueDate("2013-09-13"); 
		assertEquals(item.getDueDate(), "2013-09-13"); 
		item.setStatus("Overdue");
		assertEquals(item.getStatus(), "Overdue"); 
	}
	
	@Test
	public void AddRemoveTest() {
		Item item = new Item("Book");
		for (int i = 1; i < 100000; i++) {
			assertEquals(item.getQuantity(), i); 
			item.increaseQuantity();
		}
		for (int i = item.getQuantity(); i > 0; i--) {
			assertEquals(item.getQuantity(), i);
			item.decreaseQuanitity();
		}
		for (int i = 0; i < 100; i++) {
			item.decreaseQuanitity();; 
			assertEquals(item.getQuantity(), 0);
		}
		
	}
	
	

}
