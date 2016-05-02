/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBean;


import Ent.Product;
import beans.productBeanLocal;
import beans.NewUserBeanLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/*
Emmylou Flores 12132403
Ankit Kumar Baliyan 13009753
Joshua Hetherington 13061283
Vishrut Krashak 12085766
*/

/**
 *
 * @author ankit
 */
@Named(value = "showProduct")
@RequestScoped
public class showProduct {
   
    //persistence for showing product
    @PersistenceContext(unitName = "OnlineShoppingApplication-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    //attributes
    private String productName;
    private int productId;
    
    //object of ejb product bean
    @EJB
    private productBeanLocal productBean;
    
    //getter for product name
    public String getProductName() {
        return productName;
    }

    //setter for product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //getter for product id
    public int getProductId() {
        return productId;
    }

    //setter for product id
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    //Method to get product by name
    public List<Product> getProductByName() {
      List<Product> listProduct= productBean.getProductByName(productName);
      return listProduct;
    }

    //Method to get product by Id
    public List<Product> getProductByID() {
    List<Product> listProduct= productBean.getProductByID(productId);
      return listProduct;
    }
    
    //Method to get All product
    public List<Product> getAllProducts() {
        List<Product> listProduct= productBean.getAllProducts();
        return listProduct;
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}

   
    


