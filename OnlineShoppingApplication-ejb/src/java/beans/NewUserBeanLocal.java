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
    public boolean isCustomerExist();
    public boolean validate(String user,String pwd);
    public List<G13USERS> getCurrentUserDetails(long id);
    public long getUserID(String user,String pwd);
    public boolean update(long id, String username, String address, String message);
    public List<G13USERS> getCustomerListByName(String name);
    public List<G13USERS> getCustomerListByID(long id);
    public void setUserName(String userName);
    public String getUserName();
}
