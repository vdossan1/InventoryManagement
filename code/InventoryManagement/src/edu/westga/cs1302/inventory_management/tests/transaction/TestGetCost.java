package edu.westga.cs1302.inventory_management.tests.transaction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestGetCost {

	@Test
	public void testNoProduceAndNoFurniture() {
		Transaction transaction = new Transaction();
		
		assertEquals(0, transaction.getCost());
	}

	@Test
	public void testSomeProduceAndNoFurniture() {
		Transaction transaction = new Transaction();
		Produce produce = new Produce("produce", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(produce);
		
		assertEquals(1, transaction.getCost());
	}

	@Test
	public void testNoProduceAndSomeUnassembledFurniture() {
		Transaction transaction = new Transaction();
		Furniture furniture = new Furniture("furniture", 1, 1, false);
		transaction.addFurniture(furniture);
		
		assertEquals(1, transaction.getCost());
	}

	@Test
	public void testNoProduceAndSomeAssembledFurniture() {
		Transaction transaction = new Transaction();
		Furniture furniture = new Furniture("furniture", 1, 1, true);
		transaction.addFurniture(furniture);
		
		assertEquals(2, transaction.getCost());
	}

	@Test
	public void testSomeProduceAndSomeUnassembledFurniture() {
		Transaction transaction = new Transaction();
		Produce produce = new Produce("produce", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(produce);
		Furniture furniture = new Furniture("furniture", 1, 1, false);
		transaction.addFurniture(furniture);
		
		assertEquals(2, transaction.getCost());
	}

	@Test
	public void testSomeProduceAndSomeAssembledFurniture() {
		Transaction transaction = new Transaction();
		Produce produce = new Produce("produce", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(produce);
		Furniture furniture = new Furniture("furniture", 1, 1, true);
		transaction.addFurniture(furniture);
		
		assertEquals(3, transaction.getCost());
	}

}
