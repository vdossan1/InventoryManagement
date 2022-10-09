package edu.westga.cs1302.inventory_management.tests.inventory_serialization.XmlSerializer;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.Serializer;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.XmlSerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.Product;

class TestSerializerTransaction {

	@Test
	public void testNullTransaction() {
		Serializer serializer = new XmlSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeTransaction(null);
		});
	}

	@Test
	public void testEmptyTransaction() {
		Transaction transaction = new Transaction();
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "</Transaction>";

		assertEquals(expected, result);
	}

	@Test
	public void testTransactionSingleFurnitureNoProduce() {
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "</Transaction>";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionMultipleFurnitureNoProduce() {
		Product furniture = new Furniture(1, "name", 2, 3, false);
		Product furniture2 = new Furniture(2, "name", 2, 3, false);
		Transaction transaction = new Transaction();
		transaction.addProduct(furniture);
		transaction.addProduct(furniture2);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "<Furniture id=2 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "</Transaction>";

		assertEquals( expected, result);
	}

	@Test
	public void testTransactionNoFurnitureSingleProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Product produce = new Produce(1, "name", 2, expirationDate);
		Transaction transaction = new Transaction();
		transaction.addProduct(produce);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "</Transaction>";

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "<Produce id=2 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "</Transaction>";

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "</Transaction>";

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
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeTransaction(transaction);

		String expected = "<Transaction>" + System.lineSeparator()
							+ "<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "<Furniture id=2 name=name cost=2 assembled=false assemblyCost=3/>" + System.lineSeparator()
							+ "<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "<Produce id=2 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>" + System.lineSeparator()
							+ "</Transaction>";

		assertEquals( expected, result);
	}

}
