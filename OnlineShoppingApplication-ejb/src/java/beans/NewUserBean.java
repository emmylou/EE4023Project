/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ent.G13USERS;
import Ent.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
@Stateless
public class NewUserBean implements NewUserBeanLocal {
    @PersistenceContext(unitName = "OnlineShoppingApplication-ejbPU")
    private EntityManager em;
    //User name 
    private String userName;
    //user ID
    private long id;
    
    //Getter for id
    public long getId() {
        return this.id;
    }

    /**
     * Adds a new customer to the database with given name,
     * password, address, usertype, message
     *
     * @param username New name for new customer
     * @param password City for new customer
     * @param address State for new customer
     * @param usertype City for new customer
     * @param message State for new customer
     * @return ID of newly created customer
     */
    @Override
    public int createUser(String username,String password,String address,String usertype,String message)
    {
        //user table object
        G13USERS u = new G13USERS();
     
        //making it persistence to autogenerate id before adding details
        persist(u);
        // setting username, password, address, usertype, message
        u.setUsername(username);
        u.setPassword(password);
        u.setAddress(address);
        u.setUsertype(usertype);
        u.setMessage(message);

        // return id of new customer
        return 1;
    }
    
    //setting username
    @Override
    public void setUserName(String username)
    {
        this.userName = username;
    }
    
    //getting username
    @Override
    public String getUserName()
    {
        return this.userName;
    }
    
    /**
     * Returns list of customer with a given name
     * 
     * @return All customer with name customerName
     */
    @Override
    public boolean isCustomerExist() {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.isUserExists").setParameter("username", "joe");
        // return query result
        if((long)query.getSingleResult() > 0)
        {
          //  System.out.println("User is exists");
         return true;
        }
       // System.out.println("User Not exists");
        return false;
    }
    
    /**getting userID by passing username and password
     *
     * @param user
     * @param pwd
     * @return
     */
    @Override
    public long getUserID(String user,String pwd)
    {
        //create Query
        Query query = em.createNamedQuery("G13USERS.getUserID");
        query.setParameter("username", user);
        query.setParameter("password", pwd);
        //Execute Query
        long id = (long)query.getSingleResult();
        //setting current id 
        this.id = id;
        return id;
    }
    
    /**
     *
     * @param user
     * @param pwd
     * @return
     */
    @Override
    public boolean validate(String user,String pwd)
    {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.loginValidate");
        query.setParameter("username", user);
        query.setParameter("password", pwd);
        
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
    
    /** getting user details by passing id
     *
     * @param id
     * @return
     */
    @Override
    public List<G13USERS> getCurrentUserDetails(long id) {

        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.findByUserId")
                .setParameter("uid", id);
        //Query execute
        List<G13USERS> result = query.getResultList();
        return result;
    }
    
    /** getting customer list by name
     *
     * @param name
     * @return
     */
    @Override
    public List<G13USERS> getCustomerListByName(String name)
    {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.findByCustomerName");
        query.setParameter("username", name);
        query.setParameter("usertype", "customer");
        //Execute query
        List<G13USERS> result = query.getResultList();
        return result;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<G13USERS> getCustomerListByID(long id)
    {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.findByCustomerId");
        query.setParameter("uid", id);
        query.setParameter("usertype", "customer");
        //execute query
        List<G13USERS> result = query.getResultList();
        return result;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<G13USERS> getAllCustomer()
    {
        // create named query and set parameter
        Query query = em.createNamedQuery("G13USERS.findAllCustomer");
        query.setParameter("usertype", "customer");
        //execute query
        List<G13USERS> result = query.getResultList();
        return result;
    }
    
    
    //id, username, address, message

    /**
     *
     * @param id
     * @param username
     * @param address
     * @param message
     * @return
     */
        @Override
    public boolean update(long id, String username, String address, String message)
    {
        //System.out.println("id : "+id+" username : "+username+" Address : "+address+" message : "+message);
        //create named query and set parameter
        Query q= em.createNamedQuery("G13USERS.findByUserId");
        q.setParameter("uid", id);
        //execute query
        List <G13USERS> isin=q.getResultList();
        //checking if user in the list
        if(isin.isEmpty())
        {
           // System.out.println("List is Empty");
         return false;
        }
         else
        {  
            //System.out.println("List is not Empty");
            //int am=Integer.parseInt(amount);
            //int currentAm=isin.get(0).getQuantityOnHand();
            //Setting user details
            G13USERS u=isin.get(0);
            u.setUsername(username);
            u.setAddress(address);
            u.setMessage(message);
            em.persist(u);
          
          return true;
        }
    }
}
