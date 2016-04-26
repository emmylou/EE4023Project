/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import joshsPackage.Logging;

/**
 *
 * @author Fierce
 */

@Stateless
public class checkOutSessionBean {

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
