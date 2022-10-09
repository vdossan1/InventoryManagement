package edu.westga.cs1302.inventory_management.tests.inventory_serialization.PlainTextSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.PlainTextSerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestSerializeInventoryToFile {

	@Test
	public void testNullInventory() {
		PlainTextSerializer serializer = new PlainTextSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyInventory() throws IOException {
		InventoryManager inventory = new InventoryManager();
		PlainTextSerializer serializer = new PlainTextSerializer();
		String filename = "test-output-for-serialize-inventory-to-file.txt";

		serializer.serializeInventoryToFile(filename, inventory);

		String result = "";

		try(Scanner resultScanner = new Scanner(new File(filename))){
			while(resultScanner.hasNextLine()){
				result += resultScanner.nextLine() + System.lineSeparator();
			}
		}
		catch(IOException e){
			e.printStackTrace();
			fail("Unexpected IOException: " + e.getMessage());
		}

		assertEquals("", result);
	}

	@Test
	public void testInventoryMultipleFurnitureMultipleProduceMultipleTransactions() throws IOException {
		Transaction transaction = new Transaction();
		Transaction transaction2 = new Transaction();
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Produce produce2 = new Produce(2, "name", 2, expirationDate);
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Furniture furniture2 = new Furniture(2, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		inventory.addFurniture(furniture2);
		inventory.addProduce(produce);
		inventory.addProduce(produce2);
		inventory.addCompletedTransaction(transaction);
		inventory.addCompletedTransaction(transaction2);
		PlainTextSerializer serializer = new PlainTextSerializer();
		String filename = "test-output-for-serialize-inventory-to-file.txt";

		serializer.serializeInventoryToFile(filename, inventory);

		String result = "";

		try(Scanner resultScanner = new Scanner(new File(filename))){

			while(resultScanner.hasNextLine()){
				if(result.length() > 0){
					result += System.lineSeparator();
				}
				result += resultScanner.nextLine();
			}
		}
		catch(IOException e){
			e.printStackTrace();
			fail("Unexpected IOException: " + e.getMessage());
		}

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals(expected, result);

	}

}
