/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBean;

import Ent.G13USERS;
import beans.NewUserBeanLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

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
@Named(value = "userBean")
@RequestScoped
public class userBean implements Serializable {

    @EJB
    private NewUserBeanLocal newUserBean;

    //attributes of user table
    private long uid; // userid
    private String username;//username
    private String password;//password
    private String address;//address
    private String usertype;//usertype
    private String message;//message
    
    //getter for userid
    public long getUid() {
        return uid;
    }

    //setter for userid
    public void setUid(long uid) {
        this.uid = uid;
    }
  
    //getter for username
    public String getUsername() {
        return username;
    }

    //setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    //getter for password
    public String getPassword() {
        return password;
    }

    //setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    //getter for address
    public String getAddress() {
        return address;
    }

    //setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    //getter for usertype
    public String getUsertype() {
        return usertype;
    }

    //setter for usertype
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    //getter for message
    public String getMessage() {
        return message;
    }
    
    //setter for userid
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    /**
     * Creates a new customer with username , password , address , usertype , message stored in this bean
     */
    public void create() {
        uid = newUserBean.createUser(username,password,address,usertype,message);
    }

    //method to check if user exist
    public boolean isUserExist()
    {
     if(newUserBean.isCustomerExist())
     {
      return true;
     }
     else
         return false;
    }
    
    
    /**
     * Creates a new customer with name, city, state stored in this bean
     */
    public void initialize()
    {
     if(!isUserExist())
     {
       int id;
       //adding hardcoded users-password 
       id = newUserBean.createUser("joe","1D10T?","Castletroy, Limerick","customer","Hello, My name is Joe");
       id = newUserBean.createUser("ankit","ankit","Castletroy, Limerick","customer","Hello, My name is Ankit");
       id = newUserBean.createUser("josh","1D10T?","Castletroy, Limerick","customer","Hello, My name is Josh");
       id = newUserBean.createUser("emmylou","1D10T?","Castletroy, Limerick","customer","Hello, My name is Emmylou");
       id = newUserBean.createUser("vishrut","1D10T?","Castletroy, Limerick","customer","Hello, My name is Vishrut");
       //adding hardcoded user- admin
       id = newUserBean.createUser("toor","4uIdo0!","Limerick City","admin","Hello, My name is Toor and I'm admin here.");
       id = newUserBean.createUser("Anky","anky","Limerick City","admin","Hello, My name is Anky and I'm admin here.");
     }
     
    }
    
    /**
     * Creates a new instance of AddCustomerBean
     */
    public userBean() {
        
    }

    //notifiaction when user added
    public String notification() {
        if (uid == 0) return "";
        else return "New user with id " + uid + " created!!!";
    }
    
    //method to call ejb bean getting current user detail by id
    public List<G13USERS> getCurrentUserDetail(long uid)
    {
      List<G13USERS> uList = newUserBean.getCurrentUserDetails(uid);
      return uList;
    }
    
    //method to call ejb bean getting user detail by name
    public List<G13USERS> getCustomerListByName(String name)
    {
     List<G13USERS> uList = newUserBean.getCustomerListByName(name);
      return uList;
    }
    
    //method to call ejb bean getting user detail by id
    public List<G13USERS> getCustomerListByID(long id)
    {
     List<G13USERS> uList = newUserBean.getCustomerListByID(id);
      return uList;
    }
   
    //method to call ejb bean getting All user detail
    public List<G13USERS> getAllCustomerList()
    {
     List<G13USERS> uList = newUserBean.getAllCustomer();
      return uList;
    }
   
    //method to call ejb bean to set user detail
    public void setCurrentUserDetail(long uid)
    {
      List<G13USERS> uList = newUserBean.getCurrentUserDetails(uid);
      this.uid = uList.get(0).getId();
      this.username = uList.get(0).getUsername();
      this.address = uList.get(0).getAddress();
      this.message = uList.get(0).getMessage();
    }
}
