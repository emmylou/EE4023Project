/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ankit
 */
public class LoginDAO
{
 public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
                        System.out.println("After DataConnect.getConnection");
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			System.out.println("After prepare Query");
                        ps.setString(1, user);
			ps.setString(2, password);
                        
			ResultSet rs = ps.executeQuery();
                        System.out.println("After Execute Query");
			if (rs.next()) {
                                System.out.println("I'm inside the execute.next");
				//result found, means valid inputs
				return true;
			}
		    } 
                catch (Exception ex) 
                {
			System.out.println("Login errors -->"+user+" "+password+" " + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}    
}
