package edu.westga.cs1302.inventory_management.tests.inventory_manager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.InventoryManager;
import edu.westga.cs1302.inventory_management.model.Transaction;

public class TestAddTransaction {
	
	@Test
	public void testNullTransaction() {
		InventoryManager inventory = new InventoryManager();
		
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.addCompletedTransaction(null);
		});
	}

	@Test
	public void testAddToEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Transaction first = new Transaction();
		
		inventory.addCompletedTransaction(first);
		
		ArrayList<Transaction> transactionList = inventory.getTransactions();
		assertEquals( 1, transactionList.size());
		assertSame(first, transactionList.get(0));
	}

	@Test
	public void testAddToNonEmptyList() {
		InventoryManager inventory = new InventoryManager();
		Transaction first = new Transaction();
		Transaction second = new Transaction();
		inventory.addCompletedTransaction(first);

		inventory.addCompletedTransaction(second);
		
		ArrayList<Transaction> transactionList = inventory.getTransactions();
		assertEquals(2, transactionList.size());
		assertSame(first, transactionList.get(0));
		assertSame(second, transactionList.get(1));
	}

}
