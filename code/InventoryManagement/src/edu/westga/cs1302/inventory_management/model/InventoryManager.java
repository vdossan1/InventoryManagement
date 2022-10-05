package edu.westga.cs1302.inventory_management.model;

import java.util.ArrayList;

import edu.westga.cs1302.inventory_management.model.products.Furniture;
import edu.westga.cs1302.inventory_management.model.products.Produce;
import edu.westga.cs1302.inventory_management.model.products.ProductIDSamples;
import edu.westga.cs1302.inventory_management.model.products.ProductType;

/** Manages current inventory and stores all completed transactions.
 *
 * @author CS 1302
 * @version Fall 2022
 */
public class InventoryManager {
	public static final int FURNITURE_TYPE = 0;
	public static final int PRODUCE_TYPE = 1;

	private ArrayList<Produce> produce;
	private ArrayList<Furniture> furniture;
	private ArrayList<Transaction> completedTransactions;

	/** Create an empty InventoryManager
	 *
	 * @precondition none
	 * @postcondition getProduce().size() == 0 &&
	 * 				  getFurniture().size() == 0 &&
	 * 				  getTransactions().size() == 0
	 *
	 */
	public InventoryManager() {
		this.produce = new ArrayList<Produce>();
		this.furniture = new ArrayList<Furniture>();
		this.completedTransactions = new ArrayList<Transaction>();
	}

	/** Get the collection of Produce products stored in inventory
	 *
	 * @return the collection of Produce products stored in inventory
	 */
	public ArrayList<Produce> getProduce() {
		return this.produce;
	}

	/** Get the collection of Furniture products stored in inventory
	 *
	 * @return the collection of Furniture products stored in inventory
	 */
	public ArrayList<Furniture> getFurniture() {
		return this.furniture;
	}

	/** Get the collection of Transactions stored in inventory
	 *
	 * @return the collection of Transactions stored in inventory
	 */
	public ArrayList<Transaction> getTransactions() {
		return this.completedTransactions;
	}

	/** Add a new Produce product to inventory
	 *
	 * @precondition produce != null
	 * @postcondition none
	 *
	 * @param produce new Produce product to be added to inventory
	 */
	public void addProduce(Produce produce) {
		if (produce == null) {
			throw new IllegalArgumentException("invalid produce");
		}
		this.produce.add(produce);
	}

	/** Add a new Furniture product to inventory
	 *
	 * @precondition furniture != null
	 * @postcondition none
	 *
	 * @param furniture new Furniture product to be added to inventory
	 */
	public void addFurniture(Furniture furniture) {
		if (furniture == null) {
			throw new IllegalArgumentException("invalid furniture");
		}
		this.furniture.add(furniture);
	}

	/** Add a new completed Transaction to inventory
	 *
	 * @precondition furniture != null
	 * @postcondition none
	 *
	 * @param transaction new completed Transaction to be added to inventory
	 */
	public void addCompletedTransaction(Transaction transaction) {
		if (transaction == null) {
			throw new IllegalArgumentException("invalid transaction");
		}
		this.completedTransactions.add(transaction);
	}

	/** Finds the id of an item of the specified type with the specified name.
	 * Alternatively, returns an invalid id if a matching item can't be found.
	 *
	 * @precondition (type == InventoryManager.PRODUCE_TYPE || type == InventoryManager.FURNITURE_TYPE) &&
	 * 				 name != null && name.length() > 0
	 * @postcondition none
	 *
	 * @param type value indicating the type of product to find
	 * @param name name of the product to find
	 *
	 * @return id of the item ||
	 * 		   invalid id if a matching item can't be found
	 */
	public int findItemId(ProductType type, String name) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("invalid name");
		}

		if (type == ProductType.PRODUCE) {
			return this.findProduceItemId(name);
		}
		if (type == ProductType.FURNITURE) {
			return this.findFurnitureItemId(name);
		}

		return ProductIDSamples.INVALID_ID.getId();
	}

	private int findFurnitureItemId(String name) {
		for (Furniture furniture : this.furniture) {
			if (furniture.getName().equals(name)) {
				return furniture.getId();
			}
		}
		return ProductIDSamples.INVALID_ID.getId();
	}

	private int findProduceItemId(String name) {
		for (Produce produce : this.produce) {
			if (produce.getName().equals(name)) {
				return produce.getId();
			}
		}
		return ProductIDSamples.INVALID_ID.getId();
	}

	/** Remove the product with the specified id.
	 *
	 * @precondition id > 0 && item exists with the specified id
	 * @postcondition none
	 *
	 * @param id id of the item to be removed
	 */
	public void removeItemById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Invalid id to remove");
		}

		for (Produce produce : this.produce) {
			if (produce.getId() == id) {
				this.produce.remove(produce);
				return;
			}
		}

		for (Furniture furniture : this.furniture) {
			if (furniture.getId() == id) {
				this.furniture.remove(furniture);
				return;
			}
		}

		throw new IllegalArgumentException("No item exists with the specified id.");
	}

	/** Gets the produce item with the specified id.
	 *
	 * @precondition id > 0 && Produce exists with the specified id
	 * @postcondition none
	 *
	 * @param id id of the Produce item
	 *
	 * @return Produce item with the matching id
	 */
	public Produce getProduceById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Invalid produce id");
		}

		for (Produce produce : this.produce) {
			if (produce.getId() == id) {
				return produce;
			}
		}

		throw new IllegalArgumentException("Produce with the specified id does not exist.");
	}

	/** Gets the furniture item with the specified id.
	 *
	 * @precondition id > 0 && Furniture exists with the specified id
	 * @postcondition none
	 *
	 * @param id id of the Furniture item
	 *
	 * @return Furniture item with the matching id
	 */
	public Furniture getFurnitureById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Invalid furniture id");
		}

		for (Furniture furniture : this.furniture) {
			if (furniture.getId() == id) {
				return furniture;
			}
		}

		throw new IllegalArgumentException("Furniture with the specified id does not exist.");
	}

}
