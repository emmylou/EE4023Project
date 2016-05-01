/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ent.G13USERS;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ankit
 */
@Local
public interface NewUserBeanLocal {
    
    //method to add users
    public int createUser(String username,String password,String address,String usertype,String message);
    //checking if customer exist
    public boolean isCustomerExist();
    //check is username and password validate
    public boolean validate(String user,String pwd);
    //method to get details of current user
    public List<G13USERS> getCurrentUserDetails(long id);
    //getting current userId
    public long getUserID(String user,String pwd);
    //profile editing method
    public boolean update(long id, String username, String address, String message);
    //customer list by name
    public List<G13USERS> getCustomerListByName(String name);
    //customer list by ID
    public List<G13USERS> getCustomerListByID(long id);
    //setting user name
    public void setUserName(String userName);
    //getting username
    public String getUserName();
    //All customer list 
    public List<G13USERS> getAllCustomer();
}
