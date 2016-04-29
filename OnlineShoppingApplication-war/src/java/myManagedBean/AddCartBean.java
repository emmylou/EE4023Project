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

    @EJB
    ShoppingCartBeanLocal shoppingCartBean;
    /**
     * Creates a new instance of AddCartBean
     */
    public AddCartBean() {
    }
    
    private int quantityVar = 0;
    private int quantityVar1 = 0;
    //private int quantityPC = 0;
    //private int quantityPrinter = 0;
    //private int quantityMonitor = 0;

    public int getQuantityVar1() {
        return quantityVar1;
    }

    public void setQuantityVar1(int quantityVar1) {
        this.quantityVar1 = quantityVar1;
    }

    public int getQuantityVar() {
        return quantityVar;
    }

    public void setQuantityVar(int quantityVar) {
        this.quantityVar = quantityVar;
    }

    private String order = "";
    // use dependency injection to connect to
    // stateful session bean ShoppingCartBean
    //@EJB
    //ShoppingCartBeanLocal shoppingCartBean;


    /**
     * Adds new items to the shopping shoppingCartBean - quantities are taken from instance
     * variables
     */
    public void addToBasket(String pName, int quantityVar) {
       // shoppingCartBean.addItem("PC", quantityPC);
       // shoppingCartBean.addItem("Monitor", quantityMonitor);
       // shoppingCartBean.addItem("Printer", quantityPrinter);
        
        shoppingCartBean.addItem(pName, quantityVar);
        System.out.println("Product Name : " + pName + " Quantity : " + quantityVar);
        this.quantityVar = 0;
       // quantityVar1 = 0;
        // reset counter values
        //quantityPC = quantityMonitor = quantityPrinter = 0;
    }

    /**
     * Remove items from the shopping shoppingCartBean - quantities are taken from instance
     * variables. Note: ShoppingCart SFSB takes care of too large values
     */
    public void removeFromBasket(String pName, int quantityVar) {
       // shoppingCartBean.removeItem("PC", quantityPC);
        //shoppingCartBean.removeItem("Monitor", quantityMonitor);
       // shoppingCartBean.removeItem("Printer", quantityPrinter);
        
        shoppingCartBean.removeItem(pName, quantityVar);
        
        // reset counter values
       // this.quantityVar = 0;
       // this.quantityVar1 = 0;
        //quantityVar = 0;
        //quantityPC = quantityMonitor = quantityPrinter = 0;
    }
    
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
        shoppingCartBean.checkout();
        order = shoppingCartBean.getItemList().replace("<br>", "");
        shoppingCartBean.clearItems();
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
     * Getter for quantityPC
     * @return quantityPC
     */
//    public int getQuantityPC() {
//        return quantityPC;
//    }

    /**
     * Setter for quantityPC
     * @param quantityPC new value for quantityPC
     */
//    public void setQuantityPC(int quantityPC) {
//        this.quantityPC = quantityPC;
//    }

    /**
     * Getter for quantityPrinter
     * @return quantityPrinter
     */
//    public int getQuantityPrinter() {
//        return quantityPrinter;
//    }

    /**
     * Setter for quantityPrinter
     * @param quantityPrinter new value for quantityPrinter
     */
//    public void setQuantityPrinter(int quantityPrinter) {
//        this.quantityPrinter = quantityPrinter;
 //   }

    /**
     * Getter for quantityMonitor
     * @return quantityMonitor
     */
//    public int getQuantityMonitor() {
//        return quantityMonitor;
//    }

    /**
     * Setter for quantityMonitor
     * @param quantityMonitor  new value for quantityMonitor
     */
//    public void setQuantityMonitor(int quantityMonitor) {
//        this.quantityMonitor = quantityMonitor;
//    }

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
