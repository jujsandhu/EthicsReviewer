package ethicsreviewer.controller;

import java.sql.*;
import ethicsreviewer.utils.ConnectDatabase;
import com.microsoft.sqlserver.jdbc.*;

public class Login {

	ConnectDatabase connection;
	String dbUsername = null;
	
	public Login(){
		connection = new ConnectDatabase();
	}

	public void go(String user_input, String pass_input) {

		String SQL = "select username from lecturerlogin where username = '"
				+ user_input + "' " + "and pword = SHA1('" + pass_input + "')";

		ConnectDatabase connection = new ConnectDatabase();
		ResultSet rs = connection.getResults(SQL);

		try {
			if (rs.next())
				dbUsername = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.closeConnection();
		
	}

	public boolean verifyPassword(String user_input, String pass_input) {
		go(user_input, pass_input);
		if (dbUsername == null)
			return false;
		else {
			dbUsername = null;
			return true;
		}
	}
	
	public boolean verifyPassphrase(String pass_input){
		
		String SQL = "select SessionID FROM sessions WHERE Passphrase = '"+pass_input+"'";
		ResultSet rs = connection.getResults(SQL);
		int sessionID = 0;
		try {
			if (rs.next())
				sessionID = rs.getInt(1);
			
			else{ 
				connection.closeConnection();
			    return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sessionID);
		connection.closeConnection();
		CurrentSession.setSessionID(sessionID);
		return true;
	}

}
