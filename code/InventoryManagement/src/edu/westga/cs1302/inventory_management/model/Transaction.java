package edu.westga.cs1302.inventory_management.model;

import java.util.ArrayList;

import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;

/** Stores the products for a single transaction
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public class Transaction {
	private ArrayList<Produce> produce;
	private ArrayList<Furniture> furniture;
	
	/** Create an empty Transaction
	 * 
	 * @precondition none
	 * @postcondition getProduce().size() == 0 &&
	 * 				  getFurniture().size() == 0
	 * 
	 */
	public Transaction() {
		this.produce = new ArrayList<Produce>();
		this.furniture = new ArrayList<Furniture>();
	}
	
	/** Get the collection of Produce products stored in the Transaction
	 * 
	 * @return the collection of Produce products stored in the Transaction
	 */
	public ArrayList<Produce> getProduce() {
		return this.produce;
	}

	/** Get the collection of Furniture products stored in the Transaction
	 * 
	 * @return the collection of Furniture products stored in the Transaction
	 */
	public ArrayList<Furniture> getFurniture() {
		return this.furniture;
	}
	
	/** Add a new Produce product to the Transaction
	 * 
	 * @precondition produce != null
	 * @postcondition none
	 * 
	 * @param produce new Produce product to be added to the Transaction
	 */
	public void addProduce(Produce produce) {
		if (produce == null) {
			throw new IllegalArgumentException("invalid produce");
		}
		this.produce.add(produce);
	}
	
	/** Add a new Furniture product to the Transaction
	 * 
	 * @precondition furniture != null
	 * @postcondition none
	 * 
	 * @param furniture new Furniture product to be added to the Transaction
	 */
	public void addFurniture(Furniture furniture) {
		if (furniture == null) {
			throw new IllegalArgumentException("invalid furniture");
		}
		this.furniture.add(furniture);
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
		
		for (Produce produce : this.produce) {
			totalCost += produce.getCost();
		}
		
		for (Furniture furniture : this.furniture) {
			totalCost += furniture.getCost();
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
		return this.produce.size() + this.furniture.size();
	}

}
