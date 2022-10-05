package edu.westga.cs1302.inventory_management.model.products;

import java.time.LocalDate;
import java.util.Random;

/**
 * Stores information for a produce product.
 *
 * @author CS 1302
 * @version Fall 2022
 */
public class Produce {
	private int id;
	private String name;
	private int cost;
	private LocalDate expirationDate;

	/**
	 * Creates a new Produce product
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0 && expirationDate
	 *               != null
	 * @postcondition getName() == name && getCost() == cost && getExpirationDate()
	 *                == expirationDate && getId() > 0
	 *
	 * @param name           name of the product
	 * @param cost           cost of the product in pennies
	 * @param expirationDate expiration date for the product
	 */
	public Produce(String name, int cost, LocalDate expirationDate) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (cost < 1) {
			throw new IllegalArgumentException("Invalid cost");
		}
		if (expirationDate == null) {
			throw new IllegalArgumentException("Invalid date");
		}
		this.name = name;
		this.cost = cost;
		this.expirationDate = expirationDate;
		this.id = (new Random()).nextInt(Integer.MAX_VALUE) + 1;
	}

	/**
	 * Creates a new Produce product
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0 && expirationDate
	 *               != null && id > 0
	 * @postcondition getName() == name && getCost() == cost && getExpirationDate()
	 *                == expirationDate && getId() == id
	 *
	 * @param id             id of the product
	 * @param name           name of the product
	 * @param cost           cost of the product in pennies
	 * @param expirationDate expiration date for the product
	 */
	public Produce(int id, String name, int cost, LocalDate expirationDate) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("Name must not be null");
		}
		if (cost < 1) {
			throw new IllegalArgumentException("Cost must be positive");
		}
		if (expirationDate == null) {
			throw new IllegalArgumentException("Expiration date must not be null");
		}
		if (id < 1) {
			throw new IllegalArgumentException("Id must be positive");
		}
		this.name = name;
		this.cost = cost;
		this.expirationDate = expirationDate;
		this.id = id;
	}

	/**
	 * Gets the name of the Produce product
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return name of the product
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the cost of the Produce product in pennies
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return cost of the product in pennies
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * Gets the expiration date of the Produce product
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the expiration date of the product
	 */
	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Gets the id of the Produce product
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the id of the product
	 */
	public int getId() {
		return this.id;
	}

}
