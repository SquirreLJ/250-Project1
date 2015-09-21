package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryTest {

	@Test
	public void InitialTest() {
		Inventory stock = new Inventory(); 
		assertEquals(stock.getObservable().size(), 0); 
		assertEquals(stock.findItem(new Item("A")), -1);	
	}
	
	@Test
	public void FunctionTest() {
		Inventory stock = new Inventory(); 
		stock.addItem(new Item("A"));
		assertEquals(stock.getObservable().size(), 1); 
		assertEquals(stock.findItem(new Item("A")), 0); 
		assertEquals(stock.getObservable().get(0).getQuantity(), 1); 
		stock.addItem(new Item("A"));
		assertEquals(stock.findItem(new Item("A")), 0); 
		assertEquals(stock.getObservable().size(), 1); 
		assertEquals(stock.getObservable().get(0).getQuantity(), 2);
		for (int i = 0; i < 1000; i++) {
			assertEquals(stock.getObservable().get(0).getQuantity(), i + 2);
			stock.addItem(new Item("A"));
		}
		assertEquals(stock.getObservable().size(), 1); 
		stock.addItem(new Item("B"));
		assertEquals(stock.getObservable().size(), 2);
		assertEquals(stock.getObservable().get(0).getQuantity(), 1002);
		for (int i = 1002; i > 1; i--) {
			stock.removeItem(new Item("A")); 
			assertEquals(stock.getObservable().get(0).getQuantity(), i-1);
			assertEquals(stock.getObservable().size(), 2);
		}
		assertEquals(stock.findItem(new Item("A")), 0); 
		assertEquals(stock.getObservable().get(0).getQuantity(), 1);
		stock.removeItem(new Item("A")); 
		assertEquals(stock.findItem(new Item("A")), -1); 
		for (int i = 0; i < 1000; i++) {
			assertEquals(stock.getObservable().get(0).getQuantity(), i + 1);
			stock.addItem(new Item("B"));
		}
		Item thing = new Item("C");
		stock.addItem(thing);
		assertTrue(stock.getObservable().contains(thing));
		stock.deleteItem(new Item("B"));
		assertEquals(stock.findItem(new Item("B")), -1);
		Inventory other = new Inventory(); 
		other.addItem(new Item("D"));
		assertEquals(other.findItem(thing), -1);
		assertEquals(stock.findItem(new Item("D")), -1); 
		stock.moveItem(thing, other);
		assertEquals(stock.findItem(thing), -1);
		assertTrue(other.findItem(thing) > -1);
		assertTrue(other.findItem(new Item("D")) > -1);
	}

}
