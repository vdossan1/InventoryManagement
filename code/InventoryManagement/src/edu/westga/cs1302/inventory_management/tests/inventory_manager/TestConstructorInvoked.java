package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestConstructorInvoked {

	@Test
	public void testDefaultCase() {
		InventoryManager result = new InventoryManager();
		
		ArrayList<Produce> produceList = result.getProduce();
		assertEquals(0, produceList.size());
		ArrayList<Furniture> furnitureList = result.getFurniture();
		assertEquals( 0, furnitureList.size());
		ArrayList<Transaction> transactionList = result.getTransactions();
		assertEquals( 0, transactionList.size());
	}

}
