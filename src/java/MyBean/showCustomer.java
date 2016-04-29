/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBean;

import Entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vish
 */
@Named(value = "showCustomer")
@RequestScoped
public class showCustomer {

    @PersistenceContext(unitName = "OnlineShoppingApplicationPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    
    
    private String custName;
    private int custID;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }
    
    public List<Customer> getCustomerByName() {

        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findByName")
                .setParameter("name", custName);
        // return query result
        return query.getResultList();
    }
    
     public List<Customer> getCustomerByID() {

        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", custID);
        // return query result
        return query.getResultList();
    }
     
     public List<Customer> getAllCustomers() {

        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findAll");
        // return query result
        return query.getResultList();
    }
    
}
