package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductIDSamples;

public class TestRemoveItemById {

	@Test
	public void testInvalidId() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeItemById(ProductIDSamples.INVALID_ID.getId());
		});
	}

	@Test
	public void testItemDoesNotExist() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeItemById(ProductIDSamples.VALID_ID.getId());
		});
	}

	@Test
	public void testFurnitureItemExists() {
		InventoryManager inventory = new InventoryManager();
		Furniture furniture = new Furniture("name", 1, 1, false);
		inventory.addFurniture(furniture);

		inventory.removeItemById(furniture.getId());

		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals( 0, furnitureList.size());
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

		inventory.removeItemById(first.getId());

		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals(2, furnitureList.size());
		assertSame( middle, furnitureList.get(0));
		assertSame( last, furnitureList.get(1));
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

		inventory.removeItemById(middle.getId());

		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals( 2, furnitureList.size());
		assertSame(first, furnitureList.get(0));
		assertSame(last, furnitureList.get(1));
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

		inventory.removeItemById(last.getId());

		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals(2, furnitureList.size());
		assertSame(first, furnitureList.get(0));
		assertSame( middle, furnitureList.get(1));
	}

	@Test
	public void testProduceItemExists() {
		InventoryManager inventory = new InventoryManager();
		Produce produce = new Produce("name", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(produce);

		inventory.removeItemById(produce.getId());

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals( 0, produceList.size());
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

		inventory.removeItemById(first.getId());

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals( 2, produceList.size());
		assertSame( middle, produceList.get(0));
		assertSame( last, produceList.get(1));
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

		inventory.removeItemById(middle.getId());

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals( 2, produceList.size());
		assertSame( first, produceList.get(0));
		assertSame(last, produceList.get(1));
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

		inventory.removeItemById(last.getId());

		ArrayList<Produce> produceList = inventory.getProduce();
		assertEquals(2, produceList.size());
		assertSame( first, produceList.get(0));
		assertSame(middle, produceList.get(1));
	}

}
