package edu.westga.cs1302.inventory_management.tests.inventory_serialization.XmlSerializer;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.Serializer;
import edu.westga.cs1302.inventory_management.model.inventory_serialization.XmlSerializer;
import edu.westga.cs1302.inventory_management.model.products.Produce;

class TestSerializeProduce {

	@Test
	public void testNullProduce() {
		Serializer serializer = new XmlSerializer();

		assertThrows(IllegalArgumentException.class, () -> {
			serializer.serializeProduce(null);
		});
	}

	@Test
	public void testValidProduce() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce produce = new Produce(1, "name", 2, expirationDate);
		Serializer serializer = new XmlSerializer();

		String result = serializer.serializeProduce(produce);

		assertEquals("<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>", result);
	}

}
