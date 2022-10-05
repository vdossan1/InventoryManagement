package edu.westga.cs1302.inventory_management.tests.inventory_serialization.inventory_serializer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;

public class TestSerializeTransaction {

	@Test
	public void testNullTransaction() {
		InventorySerializer serializer = new InventorySerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyTransaction() {
		Transaction transaction = new Transaction();
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals(expected, result);
	}

	@Test
	public void testTransactionSingleFurnitureNoProduce() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addFurniture(furniture);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionMultipleFurnitureNoProduce() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Furniture furniture2 = new Furniture(2, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addFurniture(furniture);
		transaction.addFurniture(furniture2);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3"  + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionNoFurnitureSingleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Transaction transaction = new Transaction();
		transaction.addProduce(produce);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionNoFurnitureMultipleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Produce produce2 = new Produce(2, "name", 2, expirationDate);
		Transaction transaction = new Transaction();
		transaction.addProduce(produce);
		transaction.addProduce(produce2);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionSingleFurnitureSingleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addFurniture(furniture);
		transaction.addProduce(produce);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionMultipleFurnitureMultipleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Produce produce2 = new Produce(2, "name", 2, expirationDate);
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Furniture furniture2 = new Furniture(2, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addFurniture(furniture);
		transaction.addFurniture(furniture2);
		transaction.addProduce(produce);
		transaction.addProduce(produce2);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3"  + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

}
