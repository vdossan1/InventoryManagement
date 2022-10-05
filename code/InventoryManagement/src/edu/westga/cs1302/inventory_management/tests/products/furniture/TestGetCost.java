package edu.westga.cs1302.inventory_management.tests.products.furniture;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestGetCost {
	
	@Test
	public void testValidArgumentsIsAssembled() {
		Furniture furniture = new Furniture("name", 1, 1, true);
		
		int result = furniture.getCost();
		
		assertEquals( 2, result);
	}
	
	@Test
	public void testValidArgumentsIsNotAssembled() {
		Furniture furniture = new Furniture("name", 1, 1, false);
		
		int result = furniture.getCost();
		
		assertEquals(1, result);
	}

}
