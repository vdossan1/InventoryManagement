package edu.westga.cs1302.inventory_management.tests.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.Product;

class TestAddProduct {

	@Test
	public void testNullProduct() {
		Transaction transaction = new Transaction();
		
		assertThrows(IllegalArgumentException.class, () -> {
			transaction.addProduct(null);
		});
	}

	@Test
	public void testAddToEmptyList() {
		Transaction transaction = new Transaction();
		Product first = new Furniture("first", 1, 1, false);
		
		transaction.addProduct(first);
		
		ArrayList<Product> furnitureList = transaction.getProduct();
		assertEquals(1, furnitureList.size());
		assertSame(first, furnitureList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		Transaction transaction = new Transaction();
		Product first = new Furniture("first", 1, 1, false);
		Product second = new Produce("first", 1, LocalDate.of(2017, 8, 9));
		transaction.addProduct(first);
		
		transaction.addProduct(second);
		
		ArrayList<Product> furnitureList = transaction.getProduct();
		assertEquals( 2, furnitureList.size());
		assertSame(first, furnitureList.get(0));
		assertSame(second, furnitureList.get(1));
	}

}
