/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joshsPackage;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Fierce
 */
@ManagedBean
@SessionScoped
public class checkOut 
{
    //When customers check out, the quantity for your items in the database is adjusted
    //correspondingly. Make sure the quantity of a product in the database cannot drop below 0 – if
    //an order would cause this, display an error message to the user’s screen. On successful order,
    //you need to add a purchase order (PO) entry. If using the provided PO table, create a new PO for
    //each item. Alternatively, feel free to create your own PO table (see comments below)
    private Map productOrders; 
    private ArrayList<String> orders;
    
    public boolean runCheckOut(HashMap<String, Integer> cartItems)
    {
        Logging logFile;
        productOrders = cartItems;
        if(checkIfValidOrder())
        {
            //valid order so update the DB
            updateDB();
            //write to log file   the orders, admin, status
            logFile = new Logging(orders, false, true);
            return true;
        }
        else
        {
            //display error to customer that order was not successful
            
            //write to log file   the orders, admin, status
            logFile = new Logging(orders, false, false);
            return false;
        }
    }
    
    public void populateProductOrders()
    {
        //Uses orders arraylist and set it into a Map data structure, assumes the orders are in the following format,i.e, "Productname,quantity"
        String [] tempOrder;
        
        for(int i = 0; i < orders.size(); i++)
        {
            tempOrder = orders.get(i).split(",");
            productOrders.put(tempOrder[0], tempOrder[1]);
        }
    }
    
    public ArrayList<String> getOrders()
    {
        return orders;
    }
    
    public void setOrders(ArrayList<String> orders)
    {
        this.orders = orders;
    }
    
    public boolean checkIfValidOrder()
    {
        //Need to query DB to see if the order can be completed
        boolean validOrder;
        
        validOrder = true;
        /*if(DBCheck)
        {
            validOrder = true;
        }
        else
        {
            return validOrder;
        }*/
        return validOrder;
    }
    
    public void updateDB()
    {
        //update the DB with the new order(decrement the quantity of the ordered products)
        
        createPOEntry();
    }
    
    public void createPOEntry()
    {
        
    }
}
