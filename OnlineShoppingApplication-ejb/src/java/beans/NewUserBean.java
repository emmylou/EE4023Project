/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ent.G13USERS;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ankit
 */
@Stateless
public class NewUserBean implements NewUserBeanLocal {
    @PersistenceContext(unitName = "OnlineShoppingApplication-ejbPU")
    private EntityManager em;

    /**
     * Adds a new customer to the database with given name and city. Note:
     * Required foreign keys DiscountCode and MicroMarket for the table Customer
     * are fixed in this example.
     *
     * @param username New name for new customer
     * @param password City for new customer
     * @param address State for new customer
     * @param usertype City for new customer
     * @param message State for new customer
     * @return ID of newly created customer
     */
    public int createUser(String username,String password,String address,String usertype,String message)
    {
      //  int id = (Integer) em.createNamedQuery("G13USERS.getHighestUserID").getSingleResult();
        // id is current highest, increment to next id
      //  id++;
        // create customer object
      //  G13USERS u = new G13USERS(id);

        G13USERS u = new G13USERS();
        // ensure all constraints are fulfilled before making object persistent
        // make new customer persistent
        // this creates a new entry in the DB at the end of the current
        // transaction
        persist(u);
        // set city and name and state
        u.setUsername(username);
        u.setPassword(password);
        u.setAddress(address);
        u.setUsertype(usertype);
        u.setMessage(message);

        // return id of new customer
        return 1;

    }
    
    /**
     * Returns list of customer with a given name
     * 
     * @return All customer with name customerName
     */
    public boolean isCustomerExist() {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.isUserExists").setParameter("username", "joe");
        // return query result
        if((long)query.getSingleResult() > 0)
        {
            return true;
        }
        return false;
    }
    
    public boolean validate(String user,String pwd)
    {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.loginValidate");
        query.setParameter("username", user);
        query.setParameter("password", pwd);
        //query.setParameter("usertype", userType);
        
        // return query result
        if((long)query.getSingleResult() > 0)
        {
         //   System.out.println("User is exists");
         return true;
        }
        //System.out.println("User Not exists");
        return false;
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
}
