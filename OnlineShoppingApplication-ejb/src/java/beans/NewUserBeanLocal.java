/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
}
