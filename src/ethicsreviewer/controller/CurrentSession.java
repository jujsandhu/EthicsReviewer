package ethicsreviewer.controller;

import java.sql.ResultSet;
import java.util.HashMap;

import ethicsreviewer.utils.ConnectDatabase;

public class CurrentSession {

	private static int sessionID;
	private static int eventID;
	private String session[][];

	public static void setSessionID(int id) {
		sessionID = id;
	}

	public static void setEventID(int id) {
		eventID = id;
	}

	public static int getSessionID() {
		return sessionID;
	}

	public static int getEventID() {
		return eventID;
	}

	public String[][] getSessionList() {
		int sessionQuantity = 0;
		ConnectDatabase connection = new ConnectDatabase();
		ResultSet rs = connection.getResults("SELECT COUNT(*) FROM responses");

		try {
			rs.next();
			sessionQuantity = 5;
			session = new String[sessionQuantity][4];
			connection.closeConnection();
			rs = connection.getResults("SELECT * FROM sessions");

			while (rs.next()) {

				for (int i = 0; i < sessionQuantity; i++) {
					for (int j = 0; j < 4; j++) {
						session[i][j] = rs.getString(++j);
						--j;
					}
					rs.next();
				}
			}
		} catch (Exception e) {
		}
		connection.closeConnection();
		return session;
	}
	
	public void print(int sessionQuantity) {
		for (int i = 0; i < sessionQuantity; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(session[i][j]);
			}
			System.out.println();
		}
	}

}
