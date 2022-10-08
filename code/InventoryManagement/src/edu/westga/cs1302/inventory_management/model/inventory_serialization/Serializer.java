package edu.westga.cs1302.inventory_management.model.inventory_serialization;

import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs1302.inventory_management.model.InventoryManager;

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
}
