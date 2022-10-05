package edu.westga.cs1302.inventory_management.tests.products.produce;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.inventory_management.model.products.Produce;

public class TestSpecifiedIdConstructorInvoked {

	@Test
	public void testIdIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(0, null, 1, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testIdIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(-1, null, 1, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(1, null, 1, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(1, "", 1, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testCostIsZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(1, "name", 0, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testCostIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(1, "name", -1, LocalDate.of(2017, 02, 12));
		});
	}

	@Test
	public void testExpirationDateIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produce(1, "name", 1, null);
		});
	}

	@Test
	public void testValidArguments() {
		LocalDate expirationDate = LocalDate.of(2017, 02, 12);
		Produce result = new Produce(1, "name", 1, expirationDate);

		assertEquals(1, result.getId());
		assertEquals("name", result.getName());
		assertEquals(1, result.getCost());
		assertEquals(expirationDate.getMonth(), result.getExpirationDate().getMonth());
		assertEquals(expirationDate.getDayOfMonth(), result.getExpirationDate().getDayOfMonth());
		assertEquals(expirationDate.getYear(), result.getExpirationDate().getYear());
	}

}
