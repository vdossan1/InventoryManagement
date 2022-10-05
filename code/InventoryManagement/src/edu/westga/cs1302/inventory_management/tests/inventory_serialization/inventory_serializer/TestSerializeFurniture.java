package edu.westga.cs1302.inventory_management.tests.inventory_serialization.inventory_serializer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestSerializeFurniture {

	@Test
	public void testNullFurniture() {
		InventorySerializer serializer = new InventorySerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeProduce(null);
		});
	}

	@Test
	public void testValidFurniture() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		InventorySerializer serializer = new InventorySerializer();

		String result = serializer.serializeFurniture(furniture);

		assertEquals("FURNITURE 1 name 2 false 3", result);
	}

}
