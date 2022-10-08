package edu.westga.cs1302.inventory_management.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductType;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.PlainTextSerializer;
import edu.westga.cs1302.inventory_management.model.InventoryManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Maps Model behavior to the MainWindow view.
 *
 * @author CS 1302
 * @version Fall 2022
 */
public class MainWindow {
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextField nameText;
	@FXML
	private TextField costText;
	@FXML
	private Button addProduceButton;
	@FXML
	private TextField assemblyCostText;
	@FXML
	private CheckBox isAssembledCheckbox;
	@FXML
	private Button addFurnitureButton;
	@FXML
	private Button findProduceButton;
	@FXML
	private Button findFurnitureButton;
	@FXML
	private Button addToTransactionButton;
	@FXML
	private TextField itemIdText;
	@FXML
	private Button completeTransactionButton;
	@FXML
	private TextArea outputTextArea;
	@FXML
	private Button saveButton;
	@FXML
	private TextField filenameText;
	@FXML
	private Button loadButton;
	@FXML
	private Button displayInventoryButton;
	@FXML
	private Button displayTransactionButton;
	@FXML
	private ChoiceBox<String> typeComboBox;
	@FXML
	private TextField daysUnitlExpiration;
    @FXML
    private ComboBox<PlainTextSerializer> fileType;

	private InventoryManager inventory;
	private Transaction currentTransaction;

	/**
	 * Adds a new Furniture item with the specified information to the inventory,
	 * and displays the Produce item in the output text area. If the specified
	 * information is invalid, an appropriate error message is displayed in the
	 * output text area.
	 *
	 * @precondition none
	 * @postcondition Adds a new Furniture item with the specified information to
	 *                the inventory, and displays the Produce item in the output
	 *                text area. If the specified information is invalid, an
	 *                appropriate error message is displayed in the output text
	 *                area.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void addFurniture(ActionEvent event) {
		String name = this.nameText.getText();
		int cost = Integer.parseInt(this.costText.getText());
		boolean assembled = this.isAssembledCheckbox.isSelected();
		int assemblyCost = Integer.parseInt(this.assemblyCostText.getText());
		try {
			Furniture furniture = new Furniture(name, cost, assemblyCost, assembled);
			this.inventory.addFurniture(furniture);
			this.outputTextArea.setText(this.fileType.getValue().serializeFurniture(furniture));
		} catch (IllegalArgumentException e) {
			this.outputTextArea.setText(e.getMessage());
		}
	}

	/**
	 * Adds a new Produce item with the specified information to the inventory, and
	 * displays the Produce item in the output text area. If the specified
	 * information is invalid, an appropriate error message is displayed in the
	 * output text area.
	 *
	 * @precondition none
	 * @postcondition Adds a new Produce item with the specified information to the
	 *                inventory, and displays the Produce item in the output text
	 *                area. If the specified information is invalid, an appropriate
	 *                error message is displayed in the output text area.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void addProduce(ActionEvent event) {
		String name = this.nameText.getText();
		int cost = Integer.parseInt(this.costText.getText());
		int daysUntilExpiration = Integer.parseInt(this.daysUnitlExpiration.getText());
		LocalDate expirationDate = LocalDate.now().plusDays(daysUntilExpiration);
		try {
			Produce produce = new Produce(name, cost, expirationDate);
			this.inventory.addProduce(produce);
			this.outputTextArea.setText(this.fileType.getValue().serializeProduce(produce));
		} catch (IllegalArgumentException e) {
			this.outputTextArea.setText(e.getMessage());
		}
	}

	/**
	 * If an item with the selected type and id exists in inventory: Adds the item
	 * to the current transaction. Removes the item from inventory. Displays the
	 * item to the output text area. If an item with the selected type and id does
	 * not exist: Displays "NO SUCH ITEM" to the output text area.
	 *
	 * @precondition none
	 * @postcondition Item with the selected type and id is added to the current
	 *                transaction.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void addToTransaction(ActionEvent event) {
		if (this.typeComboBox.getSelectionModel().getSelectedItem().equals("Furniture")) {
			this.addFurnitureToTransaction(Integer.parseInt(this.itemIdText.getText()));
		}
		if (this.typeComboBox.getSelectionModel().getSelectedItem().equals("Produce")) {
			this.addProduceToTransaction(Integer.parseInt(this.itemIdText.getText()));
		}
	}

	private void addProduceToTransaction(int id) {
		Produce item = this.inventory.getProduceById(id);
		if (item != null) {
			this.currentTransaction.addProduct(item);
			this.inventory.removeItemById(item.getId());
			this.outputTextArea.setText(this.fileType.getValue().serializeProduce(item));
		} else {
			this.outputTextArea.setText("NO SUCH ITEM");
		}
	}

	private void addFurnitureToTransaction(int id) {
		Furniture item = this.inventory.getFurnitureById(id);
		if (item != null) {
			this.currentTransaction.addProduct(item);
			this.inventory.removeItemById(item.getId());
			this.outputTextArea.setText(this.fileType.getValue().serializeFurniture(item));
		} else {
			this.outputTextArea.setText("NO SUCH ITEM");
		}
	}

	/**
	 * Add the current transaction to the inventory, create a new (empty)
	 * transaction to be the current transaction, and display the transaction just
	 * added to the inventory in the text area
	 *
	 * @precondition none
	 * @postcondition inventory contains the transaction current transaction is set
	 *                to a new (empty) transaction transaction added to inventory is
	 *                displayed in the text area
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void completeTransaction(ActionEvent event) {
		this.inventory.addCompletedTransaction(this.currentTransaction);
		this.outputTextArea.setText(this.fileType.getValue().serializeTransaction(this.currentTransaction));
		this.currentTransaction = new Transaction();
	}

	/**
	 * Display the contents of the inventory in the text area.
	 *
	 * @precondition none
	 * @postcondition display the contents of the inventory in the text area
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void displayInventory(ActionEvent event) {
		this.outputTextArea.setText(this.fileType.getValue().serializeInventory(this.inventory));
	}

	/**
	 * Display the contents of the current transaction in the text area.
	 *
	 * @precondition none
	 * @postcondition display the contents of the current transaction in the text
	 *                area
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void displayTransaction(ActionEvent event) {
		this.outputTextArea.setText(this.fileType.getValue().serializeTransaction(this.currentTransaction));
	}

	/**
	 * Find the furniture that has the specified name. If the furniture is found
	 * display in the output text area. If the furniture is not found display
	 * 'FURNITURE NOT FOUND' in the text area.
	 *
	 * @precondition none
	 * @postcondition If the furniture is found display in the output text area. If
	 *                the furniture is not found display 'FURNITURE NOT FOUND' in
	 *                the text area.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void findFurniture(ActionEvent event) {
		String name = this.nameText.getText();
		int id = this.inventory.findItemId(ProductType.FURNITURE, name);
		if (id == -1) {
			this.outputTextArea.setText("FURNITURE NOT FOUND");
		} else {
			Furniture furniture = this.inventory.getFurnitureById(id);
			this.outputTextArea.setText(this.fileType.getValue().serializeFurniture(furniture));
		}
	}

	/**
	 * Find the produce that has the specified name. If the produce is found display
	 * in the output text area. If the produce is not found display 'PRODUCE NOT
	 * FOUND' in the text area.
	 *
	 * @precondition none
	 * @postcondition If the produce is found display in the output text area. If
	 *                the produce is not found display 'PRODUCE NOT FOUND' in the
	 *                text area.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void findProduce(ActionEvent event) {
		String name = this.nameText.getText();
		int id = this.inventory.findItemId(ProductType.PRODUCE, name);
		if (id == -1) {
			this.outputTextArea.setText("PRODUCE NOT FOUND");
		} else {
			Produce produce = this.inventory.getProduceById(id);
			this.outputTextArea.setText(this.fileType.getValue().serializeProduce(produce));
		}
	}

	/**
	 * Stores the inventory to file. Displays "INVENTORY SAVED" if inventory is
	 * saved successfully, Displays "FAILED TO SAVE INVENTORY" if inventory is not
	 * saved successfully.
	 *
	 * @precondition none
	 * @postcondition Displays "INVENTORY SAVED" in the output text area if
	 *                inventory is saved successfully, Displays "FAILED TO SAVE
	 *                INVENTORY" in the output text area if inventory is not saved
	 *                successfully.
	 *
	 * @param event action event for this button press
	 */
	@FXML
	public void save(ActionEvent event) {
		try {
			this.fileType.getValue().serializeInventoryToFile(this.filenameText.getText(), this.inventory);
			this.outputTextArea.setText("INVENTORY SAVED");
		} catch (IOException e) {
			this.outputTextArea.setText("FAILED TO SAVE INVENTORY");
		}
	}

	@FXML
	void initialize() {
		this.verifyUIComponents();

		this.typeComboBox.getItems().add("Furniture");
		this.typeComboBox.getItems().add("Produce");
		this.typeComboBox.setValue("Furniture");

		this.inventory = new InventoryManager();
		this.currentTransaction = new Transaction();
		
		this.fileType.getItems().add(new PlainTextSerializer());
		this.fileType.setValue(this.fileType.getItems().get(0));

	}

	private void verifyUIComponents() {
		assert this.nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.costText != null : "fx:id=\"costText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.addProduceButton != null : "fx:id=\"addProduceButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.assemblyCostText != null : "fx:id=\"assemblyCostText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.isAssembledCheckbox != null : "fx:id=\"isAssembledCheckbox\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.addFurnitureButton != null : "fx:id=\"addFurnitureButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.findProduceButton != null : "fx:id=\"findProduceButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.findFurnitureButton != null : "fx:id=\"findFurnitureButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.addToTransactionButton != null : "fx:id=\"addToTransactionButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.itemIdText != null : "fx:id=\"itemIdText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.completeTransactionButton != null : "fx:id=\"completeTransactionButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.outputTextArea != null : "fx:id=\"outputTextArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.filenameText != null : "fx:id=\"filenameText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.displayInventoryButton != null : "fx:id=\"displayInventoryButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.displayTransactionButton != null : "fx:id=\"displayTransactionButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.typeComboBox != null : "fx:id=\"typeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.daysUnitlExpiration != null : "fx:id=\"daysUnitlExpiration\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.fileType != null : "fx:id=\"FILE_TYPE\" was not injected: check your FXML file 'MainWindow.fxml'.";
	}
}
