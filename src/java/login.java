/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ankit
 */
@ManagedBean
@SessionScoped
public class login {

    /**
     * Creates a new instance of login
     */
    public login() {
    }
    
        private String pwd;
	private String msg;
	private String user;

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

	//validate login
	public String validateUsernamePassword() {
		boolean valid = validate(user, pwd);
		if (valid) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("username", user);
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd"+user+" "+pwd+" "+valid,
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
        
        private static boolean validate(String user, String pwd)
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
        }
}
