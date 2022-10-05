package edu.westga.cs1302.inventory_management.tests.transaction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestAddFurniture {
	
	@Test
	public void testNullProduce() {
		Transaction transaction = new Transaction();
		
		assertThrows(IllegalArgumentException.class, () -> {
			transaction.addFurniture(null);
		});
	}

	@Test
	public void testAddToEmptyList() {
		Transaction transaction = new Transaction();
		Furniture first = new Furniture("first", 1, 1, false);
		
		transaction.addFurniture(first);
		
		ArrayList<Furniture> furnitureList = transaction.getFurniture();
		assertEquals(1, furnitureList.size());
		assertSame(first, furnitureList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		Transaction transaction = new Transaction();
		Furniture first = new Furniture("first", 1, 1, false);
		Furniture second = new Furniture("second", 1, 1, false);
		transaction.addFurniture(first);
		
		transaction.addFurniture(second);
		
		ArrayList<Furniture> furnitureList = transaction.getFurniture();
		assertEquals( 2, furnitureList.size());
		assertSame(first, furnitureList.get(0));
		assertSame(second, furnitureList.get(1));
	}

}
