package edu.westga.cs1302.inventory_management.tests.inventory_serialization.inventory_serializer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestSerializeProduce {

	@Test
	public void testNullProduce() {
		InventorySerializer serializer = new InventorySerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeProduce(null);
		});
	}

	@Test
	public void testValidProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeProduce(produce);

		assertEquals("PRODUCE 1 name 2 2 12 2017", result);
	}

}
