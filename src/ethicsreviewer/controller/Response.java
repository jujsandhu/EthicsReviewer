package ethicsreviewer.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ethicsreviewer.utils.ConnectDatabase;

public class Response {
	
	public static void uploadResponses(HashMap<Integer,String> responseList){
		String SQL = "INSERT INTO responses(SessionID,EventID,QuestionNum,Response) VALUES";
		     for(int i = 1; i<= responseList.size();i++){
				SQL+= "("+"'"+CurrentSession.getSessionID()+"','"+CurrentSession.getEventID()+"','"+i+"','"+ responseList.get(new Integer(i)) +"')";
				if(i<responseList.size()) SQL += ", ";
		     }
		ConnectDatabase connection = new ConnectDatabase();
		connection.insertToDatabase(SQL);
		connection.closeConnection();
	}
	
	public static void uploadGraphData(int qnum, int catnum, int catcount){
		 int session = CurrentSession.getSessionID(); 
		
		String SQL = "UPDATE graph SET catcount=" + catcount +" WHERE session=" + session + " AND qnum=" +qnum + "AND catnum=" + catnum + ";";		     
		ConnectDatabase connection = new ConnectDatabase();
		connection.insertToDatabase(SQL);
		connection.closeConnection();
	}
	
	public static ArrayList<String> downloadGraphData(int qnum, int catnum) throws SQLException{
		 int session = CurrentSession.getSessionID(); 
		 ArrayList<String> catcountList = new ArrayList<String>();
		String SQL = "SELECT catcount FROM graph WHERE session=" + session + " AND qnum=" +qnum + "AND catnum=" + catnum + ";";		     
		ConnectDatabase connection = new ConnectDatabase();
		ResultSet rs = connection.getResults(SQL);
		try {
			while (rs.next()) {
               catcountList.add(rs.getString(1));
			}
		} catch (Exception e) {}
		connection.closeConnection();
		return catcountList;
		
	}
	
	public static ArrayList<String> getResponseByQuestionNum(int num){
		ArrayList<String> responseList = new ArrayList<String>();
		
		String SQL = "SELECT Response FROM responses WHERE QuestionNum = '"+num+"'";
		ConnectDatabase connection = new ConnectDatabase();
		ResultSet rs = connection.getResults(SQL);
		
		try {
			while (rs.next()) {
               responseList.add(rs.getString(1));
			}
		} catch (Exception e) {}
		connection.closeConnection();
		return responseList;
	}
}
