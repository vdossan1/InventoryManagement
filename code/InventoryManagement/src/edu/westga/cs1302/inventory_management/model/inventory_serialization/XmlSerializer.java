package edu.westga.cs1302.inventory_management.model.inventory_serialization;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.Product;

/**
 * Exports InventoryManager to file or string Exports Transaction, Produce, and
 * Furniture to a XML File
 *
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class XmlSerializer implements Serializer {
	
	private static final String ID_XML = "id=";
	private static final String NAME_XML = "name=";
	private static final String COST_XML = "cost=";
	private static final String CLOSE_TAG = "/>";

	/**
	 * Converts a produce object to a XML format string representation. The string will use the
	 * following format: <Produce id=<id> name=<name> cost=<cost> expirationMonth=<expiration month> 
	 * expirationDay=<expiration day> expirationYear=<expiration year>/>
	 *
	 * @precondition produceItem != null
	 * @postcondition none
	 *
	 * @param produceItem object to be serialized
	 *
	 * @return XML format string representation of the produce object
	 */
	public String serializeProduce(Produce produceItem) {
		if (produceItem == null) {
			throw new IllegalArgumentException("Must provide a Produce object");
		}
		String produce = "<Produce ";
		produce += XmlSerializer.ID_XML + produceItem.getId() + " ";
		produce += XmlSerializer.NAME_XML + produceItem.getName() + " ";
		produce += XmlSerializer.COST_XML + produceItem.getCost() + " ";
		produce += "expirationMonth=" + produceItem.getExpirationDate().getMonthValue() + " ";
		produce += "expirationDay=" + produceItem.getExpirationDate().getDayOfMonth() + " ";
		produce += "expirationYear=" + produceItem.getExpirationDate().getYear() + XmlSerializer.CLOSE_TAG;
		return produce;
	}

	/**
	 * Converts a furniture object to a XML format string representation. The string will use
	 * the following format: <Furniture id=<id> name=<name> cost=<cost> assembled=<assembled> 
	 * assembledCost=<assembly cost>/>
	 *
	 * @precondition furnitureItem != null
	 * @postcondition none
	 *
	 * @param furnitureItem object to be serialized
	 *
	 * @return XML format string representation of the furniture object
	 */
	public String serializeFurniture(Furniture furnitureItem) {
		if (furnitureItem == null) {
			throw new IllegalArgumentException("Must provide a Furniture object");
		}
		String furniture = "<Furniture ";
		furniture += XmlSerializer.ID_XML + furnitureItem.getId() + " ";
		furniture += XmlSerializer.NAME_XML + furnitureItem.getName() + " ";
		furniture += XmlSerializer.COST_XML + furnitureItem.getCost() + " ";
		furniture += "assembled=" + furnitureItem.isAssembled() + " ";
		furniture += "assemblyCost" + furnitureItem.getAssemblyCost() + XmlSerializer.CLOSE_TAG;
		return furniture;
	}

	/**
	 * Converts a transaction object to a string representation. The string will use
	 * the following format: BEGIN-TRANSACTION <Produce items with each item on a
	 * new line> <Furniture items with each item on a new line> END-TRANSACTION
	 * NOTE: Produce and Furniture items use the format described with the relevant
	 * serialize method.
	 *
	 * @precondition transactionItem != null
	 * @postcondition none
	 *
	 * @param transactionItem object to be serialized
	 *
	 * @return string representation of the transaction object
	 */
	public String serializeTransaction(Transaction transactionItem) {
		if (transactionItem == null) {
			throw new IllegalArgumentException("Must provide a Transaction object");
		}
		
		String transaction = "<Transaction>" + System.lineSeparator();

		for (Product productItem: transactionItem.getProduct()) {
			transaction += productItem.serialize(this) + System.lineSeparator();
		}

		transaction += "</Transaction>";

		return transaction;
	}

	/**
	 * Converts an inventory object to a string representation. The string will use
	 * the following format: <Produce items with each item on a new line> <Furniture
	 * items with each item on a new line> <Transaction items with each item
	 * beginning on a new line> NOTE: Produce, Furniture, and Transaction items use
	 * the format described with the relevant serialize method.
	 *
	 * @precondition inventoryItem != null
	 * @postcondition none
	 *
	 * @param inventoryManager object to be serialized
	 *
	 * @return string representation of the inventory object
	 */
	public String serializeInventory(InventoryManager inventoryManager) {
		if (inventoryManager == null) {
			throw new IllegalArgumentException("Must provide a InventoryManager object");
		}
		String inventory = "";
		
		inventory += "<Inventory>";
		
		for (Produce produceItem : inventoryManager.getProduce()) {
			inventory = this.addNewlineIfNotFirstItem(inventory);
			inventory += this.serializeProduce(produceItem);
		}

		for (Furniture furnitureItem : inventoryManager.getFurniture()) {
			inventory = this.addNewlineIfNotFirstItem(inventory);
			inventory += this.serializeFurniture(furnitureItem);
		}

		for (Transaction transactionItem : inventoryManager.getTransactions()) {
			inventory = this.addNewlineIfNotFirstItem(inventory);
			inventory += this.serializeTransaction(transactionItem);
		}
		
		inventory += "</Inventory>";
		return inventory;
	}

	private String addNewlineIfNotFirstItem(String inventory) {
		if (inventory.length() > 0) {
			inventory += System.lineSeparator();
		}
		return inventory;
	}

	@Override
	public String toString() {
		return "XML";
	}
}
