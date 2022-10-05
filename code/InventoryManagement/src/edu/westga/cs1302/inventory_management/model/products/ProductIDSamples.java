package edu.westga.cs1302.inventory_management.model.products;

/** Set of sample IDs
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public enum ProductIDSamples {
	INVALID_ID(-1),
	VALID_ID(1);
	
	private int id;
	
	ProductIDSamples(int id) {
		this.id = id;
	}
	
	/** Returns a sample id value for the associated constant.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a sample id value for the associated constant
	 */
	public int getId() {
		return this.id;
	}

}
