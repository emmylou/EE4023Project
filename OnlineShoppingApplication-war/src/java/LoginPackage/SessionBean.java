package LoginPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class SessionBean 
{
        //give the current session 
        public static HttpSession getSession() 
        {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

        //give the current request 
	public static HttpServletRequest getRequest() 
        {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

        //give the current user name
	public static String getUserName() 
        {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

        //give the current userid
	public static String getUserId() 
        {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("userid");
		else
			return null;
	}   
        
        //give the token detail
        public static String getToken()
        {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		return session.getAttribute("token").toString();
	}
        
        //give the user type- customer/admin
        public static String getUserType()
        {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		return session.getAttribute("type").toString();
	}
}
