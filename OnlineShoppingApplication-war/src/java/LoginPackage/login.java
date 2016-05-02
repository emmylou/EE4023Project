package LoginPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.NewUserBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import statics.post;

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
@ManagedBean
@SessionScoped
public class login implements Serializable {
    
    //making ejb newuserbean object
    @EJB
    private NewUserBeanLocal newUserBean;

    /**
     * Creates a new instance of login
     */
    
        public login() 
        {
        }
    
        //required attributes
        private String pwd;//passwword
	private String msg;//message
	private String user;//user
        private String userType;//usertype
        private String token;//token
        
        //gettter for token
        public String getToken() {
            return token;
        }
        
        //gettter for password
	public String getPwd() {
		return pwd;
	}

        //setter for password
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

        //gettter for message
	public String getMsg() {
		return msg;
	}

        //setter for message
	public void setMsg(String msg) {
		this.msg = msg;
	}

        //gettter for username
	public String getUser() {
		return user;
	}
 
        //setter for username
	public void setUser(String user) {
		this.user = user;
	}
        
        //gettter for usertype
        public String getUserType() {
            return userType;
        }

        //setter for usertype
        public void setUserType(String userType) {
            this.userType = userType;
        }
        
        //return userID by passing username and password
        public long userID()
        {
         long id = newUserBean.getUserID(user, pwd);
         return id;
        }
        
	//validate login
	public String validateUsernamePassword() 
        {
                //return true or false by validating username and password 
		boolean valid = newUserBean.validate(user, pwd);
		if (valid) {
                        //if validate http session set to current session 
			HttpSession session = SessionBean.getSession();
                        //setting username in newUserBean ejb
                        newUserBean.setUserName(user);
                        //newUserBean.getUserID(user, pwd);
                      if(user.equalsIgnoreCase("toor"))//if user logged in redirect to admin pane;
                      {   
                          post.generateToken();//generets a token 
                          this.token=post.getToken();//gets the generated token
                          session.setAttribute("username", user);
                          session.setAttribute("type","admin");//assign the uset type to the session as needs to be checked ,no user should have accses to admin panel
                          session.setAttribute("token",post.getToken());
                          session.setMaxInactiveInterval(20*60);
                          return "adminPanel";//redirect to correct user page"admin panel
                      }
                       else
                      {
                          post.generateToken();//generate token
                          this.token=post.getToken(); //get the generated token
                          session.setAttribute("username", user);
                          session.setAttribute("type","user");//assign the usertype
                          session.setAttribute("token",post.getToken());
                          session.setMaxInactiveInterval(20*60);
                     
                          return "admin";//redirect to normal user start page
                      }
			
			
		} else {//message if validate failed
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "login";
	}
       
    /**
     *
     * @param url
     */
   
  
    
}
