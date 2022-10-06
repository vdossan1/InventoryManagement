package edu.westga.cs1302.inventory_management.model.products;

/**
 * Stores information for a Furniture product
 *
 * @author CS 1302
 * @version Fall 2022
 */
public class Furniture extends Product {
	
	private int assemblyCost;
	private boolean assembled;

	/**
	 * Creates a new Furniture product.
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0 && assemblycost >
	 *               0
	 * @postcondition getName() == name && getAssemblyCost() == assemblyCost &&
	 *                isAssembled() == assembled
	 *
	 * @param name         name of the product
	 * @param cost         cost of the product in pennies
	 * @param assemblyCost assembly cost of the furniture in pennies
	 * @param assembled    if the furniture is assembled
	 */
	public Furniture(String name, int cost, int assemblyCost, boolean assembled) {
		super(name, cost);
		
		if (assemblyCost < 1) {
			throw new IllegalArgumentException("Invalid assembly cost");
		}
		this.assemblyCost = assemblyCost;
		this.assembled = assembled;
	}

	/**
	 * Creates a new Furniture product.
	 *
	 * @precondition name != null && name.length() > 0 && cost > 0 && assemblyCost >
	 *               0 && id > 0
	 *
	 * @postcondition getName() == name && getAssemblyCost() == assemblyCost &&
	 *                isAssembled() == assembled && getId() == id
	 *
	 * @param id           id of the product
	 * @param name         name of the product
	 * @param cost         cost of the product in pennies
	 * @param assemblyCost assembly cost of the furniture in pennies
	 * @param assembled    if the furniture is assembled
	 */
	public Furniture(int id, String name, int cost, int assemblyCost, boolean assembled) {
		super(id, name, cost);
		
		if (assemblyCost < 1) {
			throw new IllegalArgumentException("Assembly Cost must be positive");
		}
		
		this.assembled = assembled;
		this.assemblyCost = assemblyCost;
	}

	/**
	 * Gets the cost of the Furniture product in pennies
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return cost of the product in pennies if the furniture is not assembled;
	 *         cost of the product + assembly cost in pennies if the furniture is
	 *         assembled
	 */
	public int getCost() {
		if (this.assembled) {
			return this.cost + this.assemblyCost;
		}
		return this.cost;
	}

	/**
	 * Gets the assembled cost of the Furniture product
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the assembly cost of the product
	 */
	public int getAssemblyCost() {
		return this.assemblyCost;
	}

	/**
	 * Checks if the Furniture is assembled
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true if the furniture is assembled; false if the furniture is not
	 *         assembled
	 */
	public boolean isAssembled() {
		return this.assembled;
	}

}
