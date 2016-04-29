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
/**
 *
 * @author ankit
 */
@ManagedBean
@SessionScoped
public class login implements Serializable {
    
    @EJB
    private NewUserBeanLocal newUserBean;

    /**
     * Creates a new instance of login
     */
    
        public login() 
        {
        }
    
        private String pwd;
	private String msg;
	private String user;
        private String userType;
        private String token;
        
        public String getToken() {
            return token;
        }

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
        
        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
        
        public long userID()
        {
         long id = newUserBean.getUserID(user, pwd);
         return id;
        }
        
	//validate login
	public String validateUsernamePassword() 
        {
		boolean valid = newUserBean.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionBean.getSession();
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
                          post.generateToken();
                          this.token=post.getToken();
                          session.setAttribute("username", user);
                          session.setAttribute("type","user");
                          session.setAttribute("token",post.getToken());
                          session.setMaxInactiveInterval(20*60);
                     
                          return "admin";//redirect to normal user start page
                      }
			
			
		} else {
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
        
      /*  private static boolean validate(String user, String pwd)
        {
          if(user.equals("joe") & pwd.equals("1D10T?"))
          {
           return true;
          }
          else if(user.equals("toor") & pwd.equals("4uIdo0!"))
          {
           return true;
          }
          else
          {
           return false;
          }
        }*/

    /**
     *
     * @param url
     */
   
  
    
}
