package edu.westga.cs1302.inventory_management.tests.inventory_serialization.XmlSerializer;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.CharacterUtility;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.Serializer;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.XmlSerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

class TestSerializeInventory {

	@Test
	public void testNullInventory() {
		Serializer serializer = new XmlSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyInventory() {
		InventoryManager inventory = new InventoryManager();
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);
		String expected =  CharacterUtility.INVENTORY_OPENING_TAG 
							+ System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;
		
		assertEquals(expected, result);
	}

	@Test
	public void testInventorySingleFurnitureNoProduceNoTransactions() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator() 
				+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
				+ CharacterUtility.INVENTORY_CLOSING_TAG;
		
		assertEquals(expected, result);
	}

	@Test
	public void testInventoryMultipleFurnitureNoProduceNoTransactions() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Furniture furniture2 = new Furniture(2, "name", 2, 3, false);
		InventoryManager inventory = new InventoryManager();
		inventory.addFurniture(furniture);
		inventory.addFurniture(furniture2);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator() 
		+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
		+ CharacterUtility.FURNITURE_ID_TWO + System.lineSeparator()
		+ CharacterUtility.INVENTORY_CLOSING_TAG;

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryNoFurnitureSingleProduceNoTransactions() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		InventoryManager inventory = new InventoryManager();
		inventory.addProduce(produce);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;
			
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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator() 
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_TWO + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator() 
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator() 
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_TWO + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_TWO + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

		assertEquals( expected, result);
	}

	@Test
	public void testInventoryNoFurnitureNoProduceSingleTransaction() {
		Transaction transaction = new Transaction();
		InventoryManager inventory = new InventoryManager();
		inventory.addCompletedTransaction(transaction);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

		assertEquals(expected, result);
	}

	@Test
	public void testInventoryNoFurnitureNoProduceMultipleTransactions() {
		Transaction transaction = new Transaction();
		Transaction transaction2 = new Transaction();
		InventoryManager inventory = new InventoryManager();
		inventory.addCompletedTransaction(transaction);
		inventory.addCompletedTransaction(transaction2);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeInventory(inventory);

		String expected = CharacterUtility.INVENTORY_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.PRODUCE_ID_TWO + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_ONE + System.lineSeparator()
							+ CharacterUtility.FURNITURE_ID_TWO + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_OPENING_TAG + System.lineSeparator()
							+ CharacterUtility.TRANSACTION_CLOSING_TAG + System.lineSeparator()
							+ CharacterUtility.INVENTORY_CLOSING_TAG;

		assertEquals( expected, result);
	}

}
