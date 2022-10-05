package edu.westga.cs1302.inventory_management.model.inventory_serialization;

import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

/**
 * Exports InventoryManager to file or string Exports Transaction, Produce, and
 * Furniture to string
 *
 * @author CS 1302
 * @version Fall 2022
 *
 */
public class InventorySerializer {

	/**
	 * Converts an inventory object to a string representation and writes the string
	 * to file.
	 *
	 * @precondition inventoryItem != null
	 * @postcondition none
	 *
	 * @param filename  name of the output file
	 * @param inventory object to be serialized
	 *
	 */
	public void serializeInventoryToFile(String filename, InventoryManager inventory) throws IOException {
		try (FileWriter out = new FileWriter(filename)) {
			out.write(this.serializeInventory(inventory));
		}
	}

	/**
	 * Converts a produce object to a string representation. The string will use the
	 * following format: PRODUCE <id> <name> <cost> <expiration month> <expiration
	 * day> <expiration year>
	 *
	 * @precondition produceItem != null
	 * @postcondition none
	 *
	 * @param produceItem object to be serialized
	 *
	 * @return string representation of the produce object
	 */
	public String serializeProduce(Produce produceItem) {
		if (produceItem == null) {
			throw new IllegalArgumentException("Must provide a Produce object");
		}
		String produce = "PRODUCE ";
		produce += produceItem.getId() + " ";
		produce += produceItem.getName() + " ";
		produce += produceItem.getCost() + " ";
		produce += produceItem.getExpirationDate().getMonthValue() + " ";
		produce += produceItem.getExpirationDate().getDayOfMonth() + " ";
		produce += produceItem.getExpirationDate().getYear();
		return produce;
	}

	/**
	 * Converts a furniture object to a string representation. The string will use
	 * the following format: FURNITURE <id> <name> <cost> <assembled> <assembly
	 * cost>
	 *
	 * @precondition furnitureItem != null
	 * @postcondition none
	 *
	 * @param furnitureItem object to be serialized
	 *
	 * @return string representation of the furniture object
	 */
	public String serializeFurniture(Furniture furnitureItem) {
		if (furnitureItem == null) {
			throw new IllegalArgumentException("Must provide a Furniture object");
		}
		String furniture = "FURNITURE ";
		furniture += furnitureItem.getId() + " ";
		furniture += furnitureItem.getName() + " ";
		furniture += furnitureItem.getCost() + " ";
		furniture += furnitureItem.isAssembled() + " ";
		furniture += furnitureItem.getAssemblyCost();
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
		String transaction = "BEGIN-TRANSACTION" + System.lineSeparator();

		for (Produce produceItem : transactionItem.getProduce()) {
			transaction += this.serializeProduce(produceItem) + System.lineSeparator();
		}

		for (Furniture furnitureItem : transactionItem.getFurniture()) {
			transaction += this.serializeFurniture(furnitureItem) + System.lineSeparator();
		}

		transaction += "END-TRANSACTION";

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
		return "Plain Text";
	}
}
