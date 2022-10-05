package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductIDSamples;
import edu.westga.cs1302.inventory_management.model.products.ProductType;

public class TestFindItemId {

	@Test
	public void testEmptyName() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.findItemId(ProductType.FURNITURE, "");
		});
	}

	@Test
	public void testNullName() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.findItemId(ProductType.FURNITURE, null);
		});
	}

	@Test
	public void testItemDoesNotExist() {
		InventoryManager inventory = new InventoryManager();

		int result = inventory.findItemId(ProductType.FURNITURE, "name");

		assertEquals(ProductIDSamples.INVALID_ID.getId(), result);
	}

	@Test
	public void testFurnitureItemExists() {
		InventoryManager inventory = new InventoryManager();
		Furniture furniture = new Furniture("name", 1, 1, false);
		inventory.addFurniture(furniture);

		int result = inventory.findItemId(ProductType.FURNITURE, "name");

		assertEquals(furniture.getId(), result);
	}

	@Test
	public void testFindFirstFurnitureItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);

		int result = inventory.findItemId(ProductType.FURNITURE, "first");

		assertEquals(first.getId(), result);
	}

	@Test
	public void testFindMiddleFurnitureItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);

		int result = inventory.findItemId(ProductType.FURNITURE, "middle");

		assertEquals(middle.getId(), result);
	}

	@Test
	public void testFindLastFurnitureItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);

		int result = inventory.findItemId(ProductType.FURNITURE, "last");

		assertEquals(last.getId(), result);
	}

	@Test
	public void testProduceItemExists() {
		InventoryManager inventory = new InventoryManager();
		Produce produce = new Produce("name", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(produce);

		int result = inventory.findItemId(ProductType.PRODUCE, "name");

		assertEquals(produce.getId(), result);
	}

	@Test
	public void testFindFirstProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(first);
		Produce middle = new Produce("middle", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(middle);
		Produce last = new Produce("last", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(last);

		int result = inventory.findItemId(ProductType.PRODUCE, "first");

		assertEquals(first.getId(), result);
	}

	@Test
	public void testFindMiddleProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(first);
		Produce middle = new Produce("middle", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(middle);
		Produce last = new Produce("last", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(last);

		int result = inventory.findItemId(ProductType.PRODUCE, "middle");

		assertEquals(middle.getId(), result);
	}

	@Test
	public void testFindLastProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Produce first = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(first);
		Produce middle = new Produce("middle", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(middle);
		Produce last = new Produce("last", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(last);

		int result = inventory.findItemId(ProductType.PRODUCE, "last");

		assertEquals(last.getId(), result);
	}

}
