package edu.westga.cs1302.inventory_management.tests.products.furniture;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestRandomIdConstructorInvoked {

	@Test
	public void testNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(null, 1, 1, true);
		});
	}

	@Test
	public void testNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture("", 1, 1, true);
		});
	}

	@Test
	public void testCostIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture("name", 0, 1, true);
		});
	}

	@Test
	public void testCostIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture("name", -1, 1, true);
		});
	}

	@Test
	public void testAssemblyCostIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture("name", 1, -1, true);
		});
	}

	@Test
	public void testAssemblyCostIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture("name", 1, 0, true);
		});
	}

	@Test
	public void testValidArguments() {
		Furniture result = new Furniture("name", 1, 2, false);

		assertEquals("name", result.getName());
		assertEquals(1, result.getCost());
		assertEquals(2, result.getAssemblyCost());
		assertEquals(false, result.isAssembled());
		assertTrue(result.getId() > 0);
	}

}
