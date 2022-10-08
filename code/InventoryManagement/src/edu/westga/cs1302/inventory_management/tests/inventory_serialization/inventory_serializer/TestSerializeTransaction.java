package edu.westga.cs1302.inventory_management.tests.inventory_serialization.inventory_serializer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.Product;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.PlainTextSerializer;

public class TestSerializeTransaction {

	@Test
	public void testNullTransaction() {
		PlainTextSerializer serializer = new PlainTextSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyTransaction() {
		Transaction transaction = new Transaction();
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals(expected, result);
	}

	@Test
	public void testTransactionSingleFurnitureNoProduce() {
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionMultipleFurnitureNoProduce() {
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Product furniture2 = new Furniture(2, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		transaction.addProduct(furniture2);
		PlainTextSerializer serializer = new PlainTextSerializer();

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
		Product produce = new Produce(1, "name", 2, expirationDate);
		Transaction transaction = new Transaction();
		transaction.addProduct(produce);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionNoFurnitureMultipleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Product produce = new Produce(1, "name", 2, expirationDate);
		Product produce2 = new Produce(2, "name", 2, expirationDate);
		Transaction transaction = new Transaction();
		transaction.addProduct(produce);
		transaction.addProduct(produce2);
		PlainTextSerializer serializer = new PlainTextSerializer();

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
		Product produce = new Produce(1, "name", 2, expirationDate);
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		transaction.addProduct(produce);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionMultipleFurnitureMultipleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Product produce = new Produce(1, "name", 2, expirationDate);
		Product produce2 = new Produce(2, "name", 2, expirationDate);
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Product furniture2 = new Furniture(2, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		transaction.addProduct(furniture2);
		transaction.addProduct(produce);
		transaction.addProduct(produce2);
		PlainTextSerializer serializer = new PlainTextSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "BEGIN-TRANSACTION" + System.lineSeparator();
		expected += "FURNITURE 1 name 2 false 3"  + System.lineSeparator();
		expected += "FURNITURE 2 name 2 false 3"  + System.lineSeparator();
		expected += "PRODUCE 1 name 2 2 12 2017" + System.lineSeparator();
		expected += "PRODUCE 2 name 2 2 12 2017" + System.lineSeparator();
		expected += "END-TRANSACTION";

		assertEquals( expected, result);
	}

}
