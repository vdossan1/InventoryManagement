package edu.westga.cs1302.inventory_management.tests.products.produce;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;
import edu.westga.cs1302.inventory_management.model.products.Produce;

class TestSerialize {

	@Test
	public void testNullInventorySerialize() {
		
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 1, expirationDate);

		assertThrows(IllegalArgumentException.class, () -> {
			produce.serialize(null);
		});
	}

	@Test
	public void testValidInventorySerialize() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		InventorySerializer serializer = new InventorySerializer();

		String result = produce.serialize(serializer);

		assertEquals("PRODUCE 1 name 2 2 12 2017", result);
	}

}
