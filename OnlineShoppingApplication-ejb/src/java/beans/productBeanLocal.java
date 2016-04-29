/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ent.Product;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Emmylou Flores 12132403
 */
@Local
public interface productBeanLocal {
    
    public void addProduct(String title,String amount,String cost);
    public boolean removeProduct(String title);
    public boolean increment(String title,String amount);
    public boolean decrement(String title,String amount);
    public void writeToLogFile(String user, String status, String productName, String quantity);
    public List<Product> getProductByName(String name);
    public List<Product> getProductByID(int id);
    public List<Product> getAllProducts();
}
