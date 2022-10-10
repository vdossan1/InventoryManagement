package edu.westga.cs1302.inventory_management.model.inventory_serialization;

/**
 * Constants to be used in the Inventory Serialization
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class CharacterUtility {

	public static final String INVALID_COST = "Invalid cost";
	
	public static final String BEGIN_TRANSACTION = "BEGIN-TRANSACTION";
	public static final String END_TRANSACTION = "END-TRANSACTION";
	
	public static final String ID_XML = "id=";
	public static final String NAME_XML = "name=";
	public static final String COST_XML = "cost=";
	public static final String CLOSE_TAG = "/>";
	
	public static final String PRODUCE_OPEN_TAG = "<Produce ";
	public static final String FURNITURE_OPEN_TAG = "<Furniture ";
	
	public static final String TRANSACTION_OPENING_TAG = "<Transaction>";
	public static final String TRANSACTION_CLOSING_TAG = "</Transaction>";
	
	public static final String INVENTORY_OPENING_TAG = "<Inventory>";
	public static final String INVENTORY_CLOSING_TAG = "</Inventory>";
	
	public static final String FURNITURE_ID_ONE = "<Furniture id=1 name=name cost=2 assembled=false assemblyCost=3/>";
	public static final String FURNITURE_ID_TWO = "<Furniture id=2 name=name cost=2 assembled=false assemblyCost=3/>";
	
	public static final String PRODUCE_ID_ONE = "<Produce id=1 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>";
	public static final String PRODUCE_ID_TWO = "<Produce id=2 name=name cost=2 expirationMonth=2 expirationDay=12 expirationYear=2017/>";
	
}
