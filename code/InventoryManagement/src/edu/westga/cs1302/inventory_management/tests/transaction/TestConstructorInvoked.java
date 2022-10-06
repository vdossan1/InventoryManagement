package edu.westga.cs1302.inventory_management.tests.transaction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.Transaction;
import edu.westga.cs1302.inventory_management.model.products.Product;

public class TestConstructorInvoked {

	@Test
	public void testDefaultCase() {
		Transaction result = new Transaction();
		
		ArrayList<Product> produceList = result.getProduct();
		assertEquals(0, produceList.size());
	}

}
