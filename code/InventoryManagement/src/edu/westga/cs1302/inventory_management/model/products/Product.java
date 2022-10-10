package edu.westga.cs1302.inventory_management.model.products;

import java.util.Random;

import edu.westga.cs1302.inventory_management.model.inventory_serialization.Serializer;

/**
 * A project object
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public abstract class Product {
	
	private int id;
	private String name;
	
	/**
	 * Creates a new Product
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0
	 * @postcondition getName() == name && getCost() == cost && getId() > 0
	 *
	 * @param name           name of the product
	 */
	public Product(String name) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("Invalid name");
		}
		
		this.name = name;
		this.id = (new Random()).nextInt(Integer.MAX_VALUE) + 1;
	}
	
	/**
	 * Creates a new Product
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0 && id > 0
	 * @postcondition getName() == name && getCost() == cost && getId() == id
	 *
	 * @param id             id of the product
	 * @param name           name of the product
	 */
	public Product(int id, String name) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("Name must not be null");
		}
		
		if (id < 1) {
			throw new IllegalArgumentException("Id must be positive");
		}
		
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Gets the cost of the Produce product in pennies
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return cost of the product in pennies
	 */
	public abstract int getCost();
	
	/**
	 * Serialize the current product object
	 * 
	 * @precondition inventorySerializer != null
	 * @postcondition none
	 * 
	 * @param inventorySerializer to be serialized
	 * 
	 * @return string representation of the current produce object
	 */
	public abstract String serialize(Serializer inventorySerializer);
	
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
	
	/**
	 * Gets the name of the Furniture product
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return name of the product
	 */
	public String getName() {
		return this.name;
	}
}
