package ethicsreviewer.controller;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class Login {
	
   String dbUsername = null;
	
   public void go(String user_input, String pass_input){
	         String connectionUrl = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net/ethicsreviewerdb"; 
	         String username = "babe69480a9c2e";
	         String pword = "4117c86a";
	         
	         // Declare the JDBC objects.
	         Connection con = null;
	         Statement stmt = null;
	         ResultSet rs = null;

	         try {

	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(connectionUrl,username,pword);

	            //Hashed password query
	            String SQL = "select username from lecturerlogin where username = '"+user_input+"' " +
	            		"and pword = SHA1('"+pass_input+"')"; 
	            
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(SQL);

	            if(rs.next())
	               dbUsername = rs.getString(1);
	            }

	         catch (Exception e) {
	            e.printStackTrace();
	         }
	         finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	         }
    }
   
   public boolean verifyPassword(String user_input, String pass_input){
	   go(user_input, pass_input);
	   if(dbUsername == null) return false;
	   else {
		   dbUsername = null; 
		   return true;
	  }
   }
   
   
}
