package edu.westga.cs1302.inventory_management.model;

import java.util.ArrayList;

import edu.westga.cs1302.inventory_management.model.products.Product;

/** Stores the products for a single transaction
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public class Transaction {
	private ArrayList<Product> product;
	
	/** Create an empty Transaction
	 * 
	 * @precondition none
	 * @postcondition getProduct().size() == 0
	 * 
	 */
	public Transaction() {
		this.product = new ArrayList<Product>();
	}
	
	/** Get the collection of Products stored in the Transaction
	 * 
	 * @return the collection of Products stored in the Transaction
	 */
	public ArrayList<Product> getProduct() {
		return this.product;
	}

	/** Add a new Product to the Transaction
	 * 
	 * @precondition product != null
	 * @postcondition none
	 * 
	 * @param product new Product to be added to the Transaction
	 */
	public void addProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("invalid product");
		}
		this.product.add(product);
	}
	
	
	/** Gets the total cost for the transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return total cost for the transaction
	 */
	public int getCost() {
		int totalCost = 0;
		
		for (Product product : this.product) {
			totalCost += product.getCost();
		}
		
		return totalCost;
	}
	
	/** Gets the number of products stored in the Transaction.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of products stored in the Transaction
	 */
	public int size() {
		return this.product.size();
	}

}
