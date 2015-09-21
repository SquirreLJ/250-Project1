package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {

	private StringProperty name;
	private StringProperty borrower;
	private IntegerProperty quantity; 
	private StringProperty dueDate; 
	private StringProperty status;
	
	public Item(String name) {
		this.name = new SimpleStringProperty(name);
		borrower = new SimpleStringProperty("N/A");
		dueDate = new SimpleStringProperty("N/A");
		quantity = new SimpleIntegerProperty(1);
		status = new SimpleStringProperty("Pending"); 
	}
	
	public String getName() {return name.get();} 
	public StringProperty getNameProperty() {return name;}
	public String getBorrower() {return borrower.get();}
	public StringProperty getBorrowerProperty() {return borrower;}
	public int getQuantity() {return quantity.get();}
	public IntegerProperty getQuantityProperty() {return quantity;}
	public String getDueDate() {return dueDate.get();} 
	public StringProperty getDueDateProperty() {return dueDate;}
	public String getStatus() {return status.get();}
	public StringProperty getStatusProperty() {return status;}
	
	public void setName(String word) {
		name.set(word);
	}
	
	public void setBorrower(String word) {
		borrower.set(word);
	}
	
	public void setDueDate(String word) {
		dueDate.set(word);
	}
	
	public void setStatus(String word) {
		status.set(word);
	}
	
	public void increaseQuantity() {
		quantity.set(quantity.get() + 1);
	}
	
	public void decreaseQuanitity() {
		quantity.set(Math.max(0, quantity.get() - 1));
	}
	
}
