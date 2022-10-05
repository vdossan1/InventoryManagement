package edu.westga.cs1302.inventory_management.tests.transaction;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestAddProduce {
	
	@Test
	public void testNullProduce() {
		Transaction transaction = new Transaction();
		
		assertThrows(IllegalArgumentException.class, () -> {
			transaction.addProduce(null);
		});
	}

	@Test
	public void testAddToEmptyList() {
		Transaction transaction = new Transaction();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		
		transaction.addProduce(first);
		
		ArrayList<Produce> produceList = transaction.getProduce();
		assertEquals(1, produceList.size());
		assertSame( first, produceList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		Transaction transaction = new Transaction();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		Produce second = new Produce("second", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduce(first);
		
		transaction.addProduce(second);
		
		ArrayList<Produce> produceList = transaction.getProduce();
		assertEquals(2, produceList.size());
		assertSame(first, produceList.get(0));
		assertSame(second, produceList.get(1));
	}

}
