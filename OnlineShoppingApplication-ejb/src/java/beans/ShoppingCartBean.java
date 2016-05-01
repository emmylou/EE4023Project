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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ankit
 */
@Stateful
public class ShoppingCartBean implements ShoppingCartBeanLocal {
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    private HashMap<String, Integer> items = new HashMap<>();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "OnlineShoppingApplication-ejbPU")
    private EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartBean.class.getName());
    private String userName;
    
    @Override
    public HashMap<String, Integer> getCartItems() {
        return items;
    }
    
    @Override
    public void addItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity += quantity;
        items.put(id, orderQuantity);
        
        LOGGER.info("Logger Name:" + LOGGER.getName());
        String temp = String.format("%1$-20s %2$-20s %3$-20s", id, quantity, "Added into the cart");
        
        //message driven bean
        //logging
        sendJMSMessageToDest(temp);
        
        //command line server log file
        LOGGER.info(temp);
    }

    @Override
    public void removeItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity -= quantity;
        if (orderQuantity <= 0) {
            // final quantity less equal 0 - remove from cart
            items.remove(id);
            LOGGER.info("Logger Name:" + LOGGER.getName());
            String temp = String.format("%1$-20s %2$-20s %3$-20s", id, quantity, "Remove Item From Cart");
            
            //message driven bean
            //logging
            sendJMSMessageToDest(temp);

            //command line server log file
            LOGGER.info(temp);
        } 
        else 
        {
            // final quantity > 0 - adjust quantity
            items.put(id, orderQuantity);
            LOGGER.info("Logger Name:" + LOGGER.getName());
            String temp = String.format("%1$-20s %2$-20s %3$-20s", id, quantity, "Remove from cart");
            
            //message driven bean
            //logging
            sendJMSMessageToDest(temp);
        
            //command line server log file
            LOGGER.info(temp); }
        
         }

    @Override
    public String checkout() 
    {
        NewUserBean nb = new NewUserBean();
        this.userName = nb.getUserName();
        
        if(runCheckOut())
        {
            String message = getItemList();
            items.clear();
            return message;
        }
        else
        {
            items.clear();
            return getItemList();
        }
    }
    
    @Override
    public void clearItems()
    {
        //items.clear();
    }

    @Override
    public void cancel() 
    {
        // no action required - annotation @Remove indicates
        // that calling this method should remove the EJB which will
        // automatically destroy instance variables
        // empty storage
        writeToLogFile(this.userName, "Cancelled");
        items.clear();
    }

    @Override
    public String getItemList() 
    {
        if(!items.isEmpty())
        {
            System.out.println("Items not empty");
            String message = "";
            Set<String> keys = items.keySet();
            Iterator<String> it = keys.iterator();
            String k;
            while (it.hasNext()) {
                k = it.next();
                message += "Product: " + k + ", Quantity: " + items.get(k);
            }
            return message;
        }
        else
            return "No items selected or quantity execeeded!";
    }
    
    @Override
    public boolean runCheckOut()
    {
        //Logging logFile;
        if(checkIfValidOrder())
        {
            //valid order so update the DB
            updateDB();
            
            writeToLogFile(this.userName, "Confirmed");
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void writeToLogFile(String user, String status)
    {
        LOGGER.info("Logger Name:" + LOGGER.getName());
        String value = String.format("%1$-10s %2$-50s %3$-10s %4$-10s", "User", "|Product", "|Quantity", "|Status");
        //message driven bean
        //logging
        sendJMSMessageToDest(value);
        
        //command line server log file
        LOGGER.info(value);
        
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            //LOGGER.log(Level.INFO, "Order: ","\nUser: " + "\tProduct: " + pair.getKey() + "\tQuantity: " + pair.getValue() + "\tStatus: ");
            String temp = String.format("%1$-10s %2$-50s %3$-10s %4$-10s", user, "|" + pair.getKey(), "|" + pair.getValue(), "|" + status);
      
            //message driven bean
            //logging
            sendJMSMessageToDest(temp);
        
            //command line server log file
            LOGGER.info(temp);
        }
    }
    
    @Override
    public boolean checkIfValidOrder()
    {        
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) 
        {
            //System.out.println("ProductBean: inside hasNext loop");
            HashMap.Entry pair = (HashMap.Entry)it.next();
            //System.out.println("ProductBean: " + pair.getKey());
            Query q = em.createNamedQuery("Product.findByDescription");
            q.setParameter("description", pair.getKey());
            List<Product> isin = q.getResultList();
            //System.out.println("ProductBean: Query created");
            if(isin.get(0).getDescription().equals(pair.getKey()))
            {
                int orderQty = Integer.parseInt(pair.getValue().toString());
                if(isin.get(0).getQuantityOnHand() < orderQty)
                {
                    return false;
                }
            }  
        }
        return true;
    }
    
    @Override
    public void updateDB()
    {
        //update the DB with the new order(decrement the quantity of the ordered products)
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) 
        {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            decrement(pair.getKey().toString(), pair.getValue().toString());
            createPOEntry(pair.getKey().toString(), (int)pair.getValue());
        }
    }
    
    @Override
    public void createPOEntry(String desc, int qty)
    {
        NewUserBean nub = new NewUserBean();
        Query q = em.createNamedQuery("Product.findByDescription");
        q.setParameter("description", desc);
        List <Product> isin = q.getResultList();
        Product p = isin.get(0);
        long temp = 101;
        int pid = p.getProductId();
        em.persist(p);       
        createPurchaseOrder(temp, pid, qty);
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public boolean decrement(String title, String amount)
    {      
        Query q = em.createNamedQuery("Product.findByDescription");
        q.setParameter("description", title);
        List <Product> isin = q.getResultList();
        Product p = isin.get(0);
        int am = Integer.parseInt(amount);
        if(isin.isEmpty())
        {
            return false;
        }
        else
        { 
            if(p.getQuantityOnHand() <= am)
            {
                p.setQuantityOnHand(0);
                em.persist(p);
                return true;
            }
            if(p.getQuantityOnHand() > 0)
            {
                p.setQuantityOnHand(p.getQuantityOnHand() - am);
                em.persist(p);
                return true;
            }
            return true;
        }
    }
    
    @Override
    public void createPurchaseOrder(long cID, int pID, int qty) 
    {
         try
        {
         Query q= em.createNamedQuery("PurchaseOrder.getHighestPurchaseOrderID");
        int id=(int) q.getSingleResult()+1;
        
        short value = qty > Short.MAX_VALUE ? Short.MAX_VALUE : qty < Short.MIN_VALUE ? Short.MIN_VALUE : (short)qty;
        
        PurchaseOrder pr=new PurchaseOrder();
        //
        Product p=new Product(pID);
        
        Customer c = new Customer(25);
        
        Date dt = new Date();
        
        BigDecimal bValue = new BigDecimal(200);
        
        pr.setCustomerId(c);
        pr.setFreightCompany("Fedex");
        pr.setOrderNum((Integer)id);
        pr.setProductId(p);
        pr.setQuantity(value);
        pr.setSalesDate(dt);
        pr.setShippingDate(dt);
        pr.setShippingCost(bValue);
        
        em.persist(pr);     
        
        System.out.println("purchase order added");
        }
        catch(Exception ex)
        {
         System.out.println(ex.toString());
         System.out.println("purchase order not added");
        }
    }

    private void sendJMSMessageToDest(String messageData) {
        context.createProducer().send(dest, messageData);
    }
}
