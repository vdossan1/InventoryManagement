package edu.westga.cs1302.inventory_management.tests.products.furniture;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Furniture;

public class TestSpecifiedIdConstructorInvoked {

	@Test
	public void testIdIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(0, "name", 1, 1, true);
		});
	}

	@Test
	public void testIdIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(-1, "name", 1, 1, true);
		});
	}

	@Test
	public void testNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, null, 1, 1, true);
		});
	}

	@Test
	public void testNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, "", 1, 1, true);
		});
	}

	@Test
	public void testCostIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, "name", 0, 1, true);
		});
	}

	@Test
	public void testCostIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, "name", -1, 1, true);
		});
	}

	@Test
	public void testAssemblyCostIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, "name", 1, -1, true);
		});
	}

	@Test
	public void testAssemblyCostIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Furniture(1, "name", 1, 0, true);
		});
	}

	@Test
	public void testValidArguments() {
		Furniture result = new Furniture(1, "name", 1, 2, false);

		assertEquals( 1, result.getId());
		assertEquals("name", result.getName());
		assertEquals(1, result.getCost());
		assertEquals( 2, result.getAssemblyCost());
		assertEquals( false, result.isAssembled());
	}

}
