package application;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class Controller {

	private Inventory stock;
	private Inventory borrowed; 
	private Item stockSelected; 
	private Item borrowSelected;

	@FXML
	private SplitPane canvas;
	@FXML 
	private TableView<Item> StockTable;
	@FXML
	private TableColumn<Item, String> stockNameCol; 
	@FXML
	private TableColumn<Item, Integer> stockQuantCol;
	@FXML 
	private TableView<Item> BorrowedTable;
	@FXML
	private TableColumn<Item, String> borrowedNameCol;
	@FXML
	private TableColumn<Item, Integer> borrowedQuantCol;
	@FXML
	private TableColumn<Item, String> borrowedDateCol;
	@FXML
	private TableColumn<Item, String> borrowerCol; 
	@FXML
	private TableColumn<Item, String> borrowedStatusCol;
	
	@FXML
	public void initialize() {
		stock = new Inventory(); 
		
		borrowed = new Inventory(); 
		
		stockNameCol.setCellValueFactory(
				cellData -> cellData.getValue().getNameProperty());
		stockQuantCol.setCellValueFactory( 
				cellData -> cellData.getValue().getQuantityProperty().asObject());
		
		borrowedNameCol.setCellValueFactory(
				cellData -> cellData.getValue().getNameProperty());
		borrowedQuantCol.setCellValueFactory( 
				cellData -> cellData.getValue().getQuantityProperty().asObject());
		borrowedDateCol.setCellValueFactory(
				cellData -> cellData.getValue().getDueDateProperty());
		borrowerCol.setCellValueFactory(
				cellData -> cellData.getValue().getBorrowerProperty());
		borrowedStatusCol.setCellValueFactory( 
				cellData -> cellData.getValue().getStatusProperty()); 
		
		canvas.setOnKeyPressed(k -> handlePress(k.getCode()));
		
		StockTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setStockSelected(newValue));
		
		BorrowedTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setBorrowSelected(newValue));
	   
	}
	 
	
	public void changeColor() {

	}
	 
	
	@FXML
	public void handlePress(KeyCode code) {
		if (code == KeyCode.ENTER) {
			AddNewItem();
			SetBorrower();
			StockTable.setItems(stock.getObservable());
		}
		
		if (code == KeyCode.DELETE) {
			DeleteItem();
		}
		
	}
	
	public void setBorrowSelected(Item item) {
		borrowSelected = item;
	}
	
	public void setStockSelected(Item item) {
		stockSelected = item;
	}
	
	@FXML
	public void AddNewItem() { 
		if (!ItemText.getText().isEmpty()) {
			Item item = new Item(ItemText.getText()); 
			stock.addItem(item);
			ItemText.clear();
			CheckDate();
		}
	}
	
	@FXML 
	public void SetBorrower() {
		if (borrowSelected != null && 
				borrowed.getObservable().contains(borrowSelected) &&
				!BorrowerText.getText().isEmpty()) {
			borrowSelected.setBorrower(BorrowerText.getText());
		}
		BorrowerText.clear();
	}
	
	@FXML 
	public void SetDate() {
		if (borrowSelected != null &&
				borrowed.getObservable().contains(borrowSelected)) {
			borrowSelected.setDueDate(DueDate.getValue().toString()); 
			CheckDate();
		}
	}
	
	@FXML 
	public void CheckDate() {
		if (CurrentDate.getValue() != null) {
			for (int i = 0; i < borrowed.getObservable().size(); i++) {
				if (borrowed.getObservable().get(i).getDueDate().compareTo( 
						CurrentDate.getValue().toString()) < 0) {
					borrowed.getObservable().get(i).setStatus("Overdue");
				}
				else {
					borrowed.getObservable().get(i).setStatus("Pending");
				}
			}
		}
	}
	
	@FXML
	public void BorrowItem() {
		if (stockSelected != null) {
			stock.moveItem(stockSelected, borrowed);
			BorrowedTable.setItems(borrowed.getObservable());
		}
	}
		
	@FXML 
	public void ReturnItem() {
		if (borrowSelected != null) {
			borrowSelected.setDueDate("N/A");
			borrowSelected.setBorrower("N/A");
			borrowSelected.setStatus("Pending");
			borrowed.moveItem(borrowSelected, stock);
		}
	}
	
	@FXML
	public void DeleteItem() {
		if (borrowSelected != null && borrowed.getObservable().contains(borrowSelected)) {
			borrowed.deleteItem(borrowSelected);
		}
	}
	
	@FXML
	public void Add() {
		if (stockSelected != null && stock.getObservable().contains(stockSelected)) {
			stockSelected.increaseQuantity();
		}
	}
	
	@FXML 
	public void Subtract() {
		if (stockSelected != null && stock.getObservable().contains(stockSelected)) {
			stock.removeItem(stockSelected);
		}
	}
	
	@FXML 
	TextField ItemText = new TextField();
	
	@FXML 
	TextField BorrowerText = new TextField();

	@FXML
	DatePicker DueDate = new DatePicker();
	
	@FXML 
	DatePicker CurrentDate = new DatePicker();
	
}
