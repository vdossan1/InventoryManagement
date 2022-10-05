package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestAddFurniture {
	
	@Test
	public void testNullProduce() {
		InventoryManager inventory = new InventoryManager();
		assertThrows(
						IllegalArgumentException.class, 
						() -> { 
							inventory.addFurniture(null); 
						}
		);
	}

	@Test
	public void testAddToEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		
		inventory.addFurniture(first);
		
		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals(1, furnitureList.size());
		assertSame(first, furnitureList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Furniture first = new Furniture("first", 1, 1, false);
		Furniture second = new Furniture("second", 1, 1, false);
		inventory.addFurniture(first);
		
		inventory.addFurniture(second);
		
		ArrayList<Furniture> furnitureList = inventory.getFurniture();
		assertEquals(2, furnitureList.size());
		assertSame(first, furnitureList.get(0));
		assertSame(second, furnitureList.get(1));
	}

}
