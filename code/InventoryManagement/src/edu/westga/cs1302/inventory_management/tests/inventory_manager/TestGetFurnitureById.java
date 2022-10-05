package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductIDSamples;

public class TestGetFurnitureById {
	
	@Test
	public void testInvalidId() {
		InventoryManager inventory = new InventoryManager();
		
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getFurnitureById(ProductIDSamples.INVALID_ID.getId());
		});
	}
	
	@Test
	public void testNoItemsExist() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getFurnitureById(ProductIDSamples.VALID_ID.getId());
		});
	}
	
	@Test
	public void testItemIsNotFurniture() {
		InventoryManager inventory = new InventoryManager();
		Produce produce = new Produce("name", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(produce);

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getFurnitureById(produce.getId());
		});
	}
	
	@Test
	public void testFurnitureItemExists() {
		InventoryManager inventory = new InventoryManager();
		Furniture furniture = new Furniture("name", 1, 1, false);
		inventory.addFurniture(furniture);
		
		Furniture result = inventory.getFurnitureById(furniture.getId());

		assertSame(furniture, result);
	}
	
	@Test
	public void testFindFirstProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);
		
		Furniture result = inventory.getFurnitureById(first.getId());

		assertSame(first, result);
	}
	
	@Test
	public void testFindMiddleProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);
		
		Furniture result = inventory.getFurnitureById(middle.getId());

		assertSame(middle, result);
	}
	
	@Test
	public void testFindLastProduceItem() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		inventory.addFurniture(first);
		Furniture middle = new Furniture("middle", 1, 1, false);
		inventory.addFurniture(middle);
		Furniture last = new Furniture("last", 1, 1, false);
		inventory.addFurniture(last);
		
		Furniture result = inventory.getFurnitureById(last.getId());

		assertSame(last, result);
	}

}
