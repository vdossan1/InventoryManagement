
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
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public interface Serializer {
	
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
	default void serializeInventoryToFile(String filename, InventoryManager inventory) throws IOException {
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
	String serializeProduce(Produce produceItem);
	
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
	String serializeFurniture(Furniture furnitureItem);
	
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
	String serializeTransaction(Transaction transactionItem);
	
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
	String serializeInventory(InventoryManager inventoryManager);
	
	@Override
	String toString();
}
