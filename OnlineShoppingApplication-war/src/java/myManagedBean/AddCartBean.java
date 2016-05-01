/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBean;

import LoginPackage.SessionBean;
import beans.ShoppingCartBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import beans.ShoppingCartBeanLocal;
import java.util.HashMap;
import statics.post;

/**
 *
 * @author ankit
 */
@Named(value = "addCartBean")
@SessionScoped
public class AddCartBean implements Serializable {

    @EJB
    private ShoppingCartBeanLocal shoppingCartBean;
    /**
     * Creates a new instance of AddCartBean
     */
    public AddCartBean() {
    }
    
    private int quantityVar = 0;
    private int quantityVar1 = 0;
   
    //getter for quantityVar1
    public int getQuantityVar1() {
        return quantityVar1;
    }

    //setter for quantityVar1
    public void setQuantityVar1(int quantityVar1) {
        this.quantityVar1 = quantityVar1;
    }

    //getter for quantityVar
    public int getQuantityVar() {
        return quantityVar;
    }

    //setter for quantityVar
    public void setQuantityVar(int quantityVar) {
        this.quantityVar = quantityVar;
    }

    //sring order to add oreder item
    private String order = "";

    /**
     * Adds new items to the shopping shoppingCartBean - quantities are taken from instance
     * variables
     */
    public void addToBasket(String pName, int quantityVar) {
       
        //ejb beann to add item
        shoppingCartBean.addItem(pName, quantityVar);  
       
        //reset the counter 
        this.quantityVar = 0;
    }

    /**
     * Remove items from the shopping shoppingCartBean - quantities are taken from instance
     * variables. Note: ShoppingCart SFSB takes care of too large values
     */
    public void removeFromBasket(String pName, int quantityVar) {
       
        //ejb beann to add item 
        shoppingCartBean.removeItem(pName, quantityVar);
        
        //reset the counter
        this.quantityVar1 = 0;
    }
   
    //hash map list to get cart item 
    public HashMap<String, Integer> getCartItems()
    {
        return shoppingCartBean.getCartItems();
    }
    /**
     * Checkout shopping shoppingCartBean - only stores checked out items in instance
     * variable and removes releases SFSB shoppingCartBean
     *
     * @return Value "checkout" for auto navigation
     */
    public String checkout()
    {   
        //calling ejb bean checkout and add cart item into order string
        order = shoppingCartBean.checkout();
        //calling back checkout page
        return "checkout";
      
    }

    /**
     * Cancels the order. Only releases SFSB shoppingCartBean
     *
     * @return Value "cancel" for auto navigation
     */
    public String cancel() {
        shoppingCartBean.cancel();
        return "cancel";
    }

    /**
     * Returns a list of items and their quantities that are currently in
     * shopping shoppingCartBean
     *
     * @return Items/quantities in shopping shoppingCartBean
     */
    public String getItemList() {
        String content = shoppingCartBean.getItemList();
        return content.replace("<br>", "");
    }

    /**
     * Destroys current session
     *
     * @return value "index"
     */
    public String index() {
        // invalidate session to remove reference 
        // to shopping shoppingCartBean - you want a new one next time to make
        // sure to receive a new SFSB
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        return "admin";
    }


    /**
     * Getter for order
     * @return order
     */
    public String getOrder() {
        return order;
    }
    /**
     * Starts the check out functionality
     */
    public void runCheckOut()
    {
        shoppingCartBean.runCheckOut();
    }
    
}
