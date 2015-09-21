package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
	
	private ObservableList<Item> inventory = 
		FXCollections.observableArrayList();
	
	public Inventory() {};
	
	public ObservableList<Item> getObservable() {
		return inventory;
	}
	
	public int findItem(Item item) { 
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getName().equals(item.getName())) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void addItem(Item item) {
		int location = findItem(item);
		if (location == -1 || 
				(!inventory.get(location).getDueDate().equals(item.getDueDate())
				|| (!inventory.get(location).getBorrower().equals(item.getBorrower())))
						) {inventory.add(item);}
		else {
			inventory.get(location).increaseQuantity();
		}
	}
	
	public void removeItem(Item item) {
		int location = findItem(item);
		if (location > -1) {
			if (inventory.get(location).getQuantity() == 1) {
				inventory.remove(location);
			}			
			else {
			inventory.get(location).decreaseQuanitity();
			}
		}
	}
	
	public void deleteItem(Item item) {
		int location = findItem(item); 
		if (location > -1) {
			inventory.remove(location);
		}
	}
	
	public void moveItem(Item item, Inventory other) {
		if (findItem(item) > -1) {
			other.addItem(new Item(item.getName()));
			removeItem(item);
		}
	}
	
}
