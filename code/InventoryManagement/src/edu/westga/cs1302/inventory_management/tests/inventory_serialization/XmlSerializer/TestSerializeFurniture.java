package edu.westga.cs1302.inventory_management.tests.inventory_serialization.XmlSerializer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.PlainTextSerializer;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.Serializer;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.XmlSerializer;
import edu.westga.cs1302.inventory_management.model.products.Furniture;

class TestSerializeFurniture {

	@Test
	public void testNullFurniture() {
		Serializer serializer = new XmlSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeProduce(null);
		});
	}

	@Test
	public void testValidFurniture() {
		Furniture furniture = new Furniture(1, "name", 2, 3, false);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeFurniture(furniture);

		assertEquals("<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>", result);
	}

}
