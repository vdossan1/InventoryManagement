package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductIDSamples;

public class TestGetProduceById {
	
	@Test
	public void testInvalidId() {
		InventoryManager inventory = new InventoryManager();
		
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getProduceById(ProductIDSamples.INVALID_ID.getId());
		});
	}
	
	@Test
	public void testNoItemsExist() {
		InventoryManager inventory = new InventoryManager();

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getProduceById(ProductIDSamples.VALID_ID.getId());
		});
	}
	
	@Test
	public void testItemIsNotProduce() {
		InventoryManager inventory = new InventoryManager();
		Furniture furniture = new Furniture("name", 1, 1, false);
		inventory.addFurniture(furniture);

		assertThrows(IllegalArgumentException.class, () -> {
			inventory.getProduceById(furniture.getId());
		});
	}
	
	@Test
	public void testProduceItemExists() {
		InventoryManager inventory = new InventoryManager();
		Produce produce = new Produce("name", 1, LocalDate.of(2017, 8, 9));
		inventory.addProduce(produce);
		
		Produce result = inventory.getProduceById(produce.getId());

		assertSame(produce, result);
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
		
		Produce result = inventory.getProduceById(first.getId());

		assertSame(first, result);
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
		
		Produce result = inventory.getProduceById(middle.getId());

		assertSame(middle, result);
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
		
		Produce result = inventory.getProduceById(last.getId());

		assertSame(last, result);
	}

}
