package edu.westga.cs1302.inventory_management.model.products;

import java.time.LocalDate;

/**
 * Stores information for a produce product.
 *
 * @author CS 1302
 * @version Fall 2022
 */
public class Produce extends Product {
	
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
		super(name, cost);
		
		if (expirationDate == null) {
			throw new IllegalArgumentException("Invalid date");
		}
		
		this.expirationDate = expirationDate;
		
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
		super(id, name, cost);
		
		if (expirationDate == null) {
			throw new IllegalArgumentException("Expiration date must not be null");
		}
		
		this.expirationDate = expirationDate;
		
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


}
