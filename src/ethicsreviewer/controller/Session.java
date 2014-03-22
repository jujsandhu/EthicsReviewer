package ethicsreviewer.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ethicsreviewer.utils.ConnectDatabase;

public class Session {

	public static void addSession(String name, String passphrase,
			String focusgroup) {
		String SQL = "INSERT INTO sessions(SessionName,DateCreated,FocusGroup, Passphrase) VALUES";

		SQL += "(" + "'" + name + "','" + getDate() + "','" + focusgroup
				+ "','" + passphrase + "')";
		ConnectDatabase connection = new ConnectDatabase();
		connection.insertToDatabase(SQL);
		connection.closeConnection();
	}

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
		return sdf.format(date);
	}

	public static int getSessionID(String name) {
		String SQL = "SELECT SessionID FROM sessions WHERE SessionName = '"
				+ name + "'";
		ConnectDatabase connection = new ConnectDatabase();
		int id = 0;

		try {

			ResultSet rs = connection.getResults(SQL);
			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return id;
	}
}
