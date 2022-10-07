package edu.westga.cs1302.inventory_management.tests.products.furniture;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Product;

class TestSerialize {

	@Test
	public void testNullInventorySerialize() {
		
		Product furniture = new Furniture(1, "name", 2, 3, false);

		assertThrows(IllegalArgumentException.class, () -> {
			furniture.serialize(null);
		});
	}

	@Test
	public void testValidInventorySerialize() {
		
		Product furniture = new Furniture(1, "name", 2, 3, false);
		InventorySerializer serializer = new InventorySerializer();

		String result = furniture.serialize(serializer);

		assertEquals("FURNITURE 1 name 2 false 3", result);
	}

}
