package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestAddProduce {

	@Test
	public void testNullProduce() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.addProduce(null);
		});
	}

	@Test
	public void testAddToEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));

		inventory.addProduce(first);

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals(1, produceList.size());
		assertSame(first, produceList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		Produce second = new Produce("second", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(first);

		inventory.addProduce(second);

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals( 2, produceList.size());
		assertSame( first, produceList.get(0));
		assertSame( second, produceList.get(1));
	}

}
