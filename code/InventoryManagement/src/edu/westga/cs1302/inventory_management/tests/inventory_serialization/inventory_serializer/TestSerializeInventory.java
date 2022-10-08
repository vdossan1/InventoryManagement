package edu.westga.cs1302.inventory_management.tests.inventory_serialization.inventory_serializer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.PlainTextSerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestSerializeInventory {

	@Test
	public void testNullInventory() {
		PlainTextSerializer serializer = new PlainTextSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyInventory() {
		InventoryManager inventory = new InventoryManager();
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		assertEquals("", result);
	}

	@Test
	public void testInventorySingleFurnitureNoProduceNoTransactions() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "FURNITURE 1 name 2 false 3";

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryMultipleFurnitureNoProduceNoTransactions() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Furniture furniture2 = new Furniture(2, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		inventory.addFurniture(furniture2);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3";

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryNoFurnitureSingleProduceNoTransactions() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		InventoryManager inventory = new InventoryManager();
		inventory.addProduce(produce);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017";

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryNoFurnitureMultipleProduceNoTransactions() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Produce produce2 = new Produce(2, "name", 2, expirationDate);
		InventoryManager inventory = new InventoryManager();
		inventory.addProduce(produce);
		inventory.addProduce(produce2);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017";

		assertEquals(expected, result);
	}

	@Test
	public void testInventorySingleFurnitureSingleProduceNoTransactions() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		inventory.addProduce(produce);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3";

		assertEquals( expected, result);
	}

	@Test
	public void testInventoryMultipleFurnitureMultipleProduceNoTransactions() {
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
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3";

		assertEquals( expected, result);
	}

	@Test
	public void testInventoryNoFurnitureNoProduceSingleTransaction() {
		Transaction transaction = new Transaction();
		InventoryManager inventory = new InventoryManager();
		inventory.addCompletedTransaction(transaction);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryNoFurnitureNoProduceMultipleTransactions() {
		Transaction transaction = new Transaction();
		Transaction transaction2 = new Transaction();
		InventoryManager inventory = new InventoryManager();
		inventory.addCompletedTransaction(transaction);
		inventory.addCompletedTransaction(transaction2);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testInventorySingleFurnitureSingleProduceSingleTransaction() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		inventory.addProduce(produce);
		inventory.addCompletedTransaction(transaction);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryMultipleFurnitureMultipleProduceMultipleTransactions() {
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

		String result = serializer.serializeInventory(inventory);

		String expected = "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION" + System.lineSeparator();
		expected += "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

}
