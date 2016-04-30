/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author
 */
@Local
public interface ShoppingCartBeanLocal
{
      public void addItem(String id, int quantity);
    /**
     * Removes a number items from the shopping cart. If quantity exceeds
     * the number of present items, number is set to 0.
     * @param id - ID of the item to be removed
     * @param quantity - number of items to be removed. 
     */
    public void removeItem(String id, int quantity);
    /**
     * Proceed with the checkout by asking for billing information. Checkout 
     * will terminate the current session for the EJB.
     * @return message to user for check out
     */
    public String checkout();
    /**
     * Cancels the current ordering process. Cancel will terminate the current 
     * session for the EJB.
     */
    public void cancel();
    
    /**
     * Returns a string representing content of shopping cart
     * @return 
     */
    public String getItemList(); 
    
    /**
     * Returns a Hash-map of the Checkout cart items
     * @return 
     */
    public HashMap<String, Integer> getCartItems();
    
    /**
     * runs the checkout functionality
     * @return whether check out was successful
     */
    public boolean runCheckOut();
    
    /**
     * Writes to log output
     * @param user customer name/ administrator name
     * @param status status of order/ product
     */
    public void writeToLogFile(String user, String status);
    
    /**
     * returns a boolean if the order is valid or not
     * @return 
     */
    public boolean checkIfValidOrder();
    
    /**
     * updates the Database with the new order
     */
    public void updateDB();
    
    /**
     * creates a new Purchase order entry
     */
    public void createPOEntry(String desc, int qty);
    
    /**
     * decrements the product table
     * @param title - name of product
     * @param amount - quantity of product
     * @return whether decrement was successful
     */
    public boolean decrement(String title, String amount);
    
    /**
     * method to clear items
     */
    public void clearItems();
    
    /**
     * method to create new purchase order entry
     * @param cID
     * @param pID
     * @param qty
     */
    public void createPurchaseOrder(long cID, int pID, int qty);
    
}
