package edu.westga.cs1302.inventory_management.tests.transaction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestSize {

	@Test
	public void testNoProduceAndNoFurniture() {
		Transaction transaction = new Transaction();
		
		assertEquals(0, transaction.size());
	}

	@Test
	public void testSomeProduceAndNoFurniture() {
		Transaction transaction = new Transaction();
		Produce produce = new Produce("produce", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(produce);
		
		assertEquals(1, transaction.size());
	}

	@Test
	public void testNoProduceAndSomeFurniture() {
		Transaction transaction = new Transaction();
		Furniture furniture = new Furniture("furniture", 1, 1, false);
		transaction.addFurniture(furniture);
		
		assertEquals(1, transaction.size());
	}

	@Test
	public void testSomeProduceAndSomeFurniture() {
		Transaction transaction = new Transaction();
		Produce produce = new Produce("produce", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(produce);
		Furniture furniture = new Furniture("furniture", 1, 1, true);
		transaction.addFurniture(furniture);
		
		assertEquals(2, transaction.size());
	}

}
