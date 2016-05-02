/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBean;

import beans.NewUserBeanLocal;
import Ent.G13USERS;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

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
@Named(value = "editUserBean")
@SessionScoped
public class EditUserBean implements Serializable {

    //bean to edit user profile
     @EJB
    private NewUserBeanLocal newUserBean;
    
    /**
     * Creates a new instance of EditUserBean
     */
    public EditUserBean() {
        id = 1;
    }
    
    //required attributes
    private long id; //user id
    private String username; //username
    private String address; // address
    private String message; //message
    private G13USERS user; //user
    
    //getter to get id 
    public long getId() {
        return id;
    }

    //setter to set id
    public void setId(long id) {
        this.id = id;
    }

    //getter to get username
    public String getUsername() {
        return username;
    }

    //setter to set username
    public void setUsername(String username) {
        this.username = username;
    }

    //getter to get address
    public String getAddress() {
        return address;
    }

    //setter to set address
    public void setAddress(String address) {
        this.address = address;
    }

    //getter to get message
    public String getMessage() {
        return message;
    }

    //setter to set message
    public void setMessage(String message) {
        this.message = message;
    }

    //getter to get user
    public G13USERS getUser() {
        return user;
    }

    //setter to set user
    public void setUser(G13USERS user) {
        this.user = user;
    }
    
    //update user detail, calling ejb bean to change user profile
    public void changeUserDetail(long id,String username,String address,String message)
    {
      //  System.out.println("id : "+id+" username : "+username+" Address : "+address+" message : "+message);
        newUserBean.update(id, username, address, message);
    }
}
