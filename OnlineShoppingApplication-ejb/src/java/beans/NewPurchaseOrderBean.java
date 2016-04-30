/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Ent.Customer;
import Ent.G13PO;
import Ent.Manufacturer;
import Ent.Product;
import Ent.ProductCode;
import Ent.PurchaseOrder;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Fierce
 */
@Stateless
public class NewPurchaseOrderBean implements NewPurchaseOrderBeanLocal {
    @PersistenceContext(unitName = "OnlineShoppingApplication-ejbPU")
    private EntityManager em;

    @Override
    public void createPurchaseOrder(Long cID, int pID, int qty)
    {
        Query q = em.createNamedQuery("G13PO.getHighestPurchaseId");
        if(q.getSingleResult() == null)
        {
            int id = 1;
            G13PO po=new G13PO();

            po.setPurchaseOrderId(id);
            po.setUserID(cID);
            po.setCompany("Fedex");
            po.setOrderDate("2016-04-30");
            po.setProductid(pID);
            po.setQuantity(qty);

           //NewUserBean nb = new NewUserBean();
           //this.uName = nb.getUserName();
          //writeToLogFile("admin", "Added", title, amount);
           em.persist(po); 
        }
        else
        {
           int id = (int) q.getSingleResult()+1;
            G13PO po=new G13PO();

            po.setPurchaseOrderId(id);
            po.setUserID(cID);
            po.setCompany("Fedex");
            po.setOrderDate("2016-04-30");
            po.setProductid(pID);
            po.setQuantity(qty);

           //NewUserBean nb = new NewUserBean();
           //this.uName = nb.getUserName();
          //writeToLogFile("admin", "Added", title, amount);
           em.persist(po); 
    
        }
        
        
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    
}
