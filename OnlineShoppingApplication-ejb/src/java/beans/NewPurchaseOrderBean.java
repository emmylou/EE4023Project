/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ent.Customer;
import Ent.Product;
import Ent.PurchaseOrder;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fierce
 */
@Stateless
public class NewPurchaseOrderBean implements NewPurchaseOrderBeanLocal {
    
    @PersistenceContext(unitName = "OnlineShoppingApplication-ejbPU")
    private EntityManager em;

    /**
     * Adds a new customer to the database with given name and city. Note:
     * Required foreign keys DiscountCode and MicroMarket for the table Customer
     * are fixed in this example.
     *
     * @param cID
     * @param pID
     * @param qty
     * @return ID of newly created customer
     */
    @Override
    public int createPurchaseOrder(long cID, int pID, int qty) {
        int id = (Integer) em.createNamedQuery("PurchaseOrder.getHighestPurchaseOrderID").getSingleResult();
        // id is current highest, increment to next id
        id++;
        // create customer object
        PurchaseOrder po = new PurchaseOrder(id);
        /*-ORDER_NUM 
        -CUSTOMER_ID param
        -PRODUCT_ID  param
        -QUANTITY    param
        -SHIPPING_COST fix
        -SALE_DATE	get System date
        -SHIPPING_DATE  get system date 
        -FREIGHT_COMPANY fixed*/

        // ensure all constraints are fulfilled before making object persistent
        // set discount to none
        po.setShippingCost((BigDecimal) em.createNamedQuery("PurchaseOrder.findByShippingCost").
                setParameter("shippingCost", "200").getSingleResult());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);
        
        po.setSalesDate((Date) em.createNamedQuery("PurchaseOrder.findBySalesDate").
                setParameter("salesDate", date));
        
        po.setShippingDate((Date) em.createNamedQuery("PurchaseOrder.findByShippingDate").
                setParameter("shippingDate", date));
        
        String freight = "FeDex";
        po.setFreightCompany(freight);
        
        Customer c = new Customer((int)cID);
        po.setCustomerId(c);
        
        Product p = new Product(pID);
        po.setProductId(p);
        po.setQuantity((short)qty);
        // make new customer persistent
        // this creates a new entry in the DB at the end of the current
        // transaction
        persist(po);
        // set city and name and state
        //po.setCity(city);
        //c.setName(name);
        //c.setState(state);

        // return id of new customer
        return id;
    }

    /**
     * make the passed object persistent
     *
     * @param object object to be made persistent
     */
    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }
}
