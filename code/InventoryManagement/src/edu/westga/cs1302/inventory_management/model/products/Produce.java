package edu.westga.cs1302.inventory_management.model.products;

import java.time.LocalDate;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.InventorySerializer;

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
	 * @precondition  expirationDate != null
	 * @postcondition getExpirationDate() == expirationDate
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
	 * @precondition expirationDate != null
	 * @postcondition getExpirationDate() == expirationDate
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
	 *Converts the current produce object to a string representation. The string will use the
	 * following format: PRODUCE <id> <name> <cost> <expiration month> <expiration day> <expiration year>
	 * 
	 * @precondition inventorySerializer != null
	 * @postcondition none
	 * 
	 * @param inventorySerializer to be serialized
	 * 
	 * @return string representation of the current produce object
	 */
	public String serialize(InventorySerializer inventorySerializer) {
		if (inventorySerializer == null) {
			throw new IllegalArgumentException("invetory serializer cannot be null");
		}
		return inventorySerializer.serializeProduce(this);
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
