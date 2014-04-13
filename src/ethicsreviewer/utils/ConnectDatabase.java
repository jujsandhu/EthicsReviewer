package ethicsreviewer.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDatabase {
	
	// Declare the JDBC objects.
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
//    String connectionUrl = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net/ethicsreviewerdb"; 
//    String username = "babe69480a9c2e";
//    String pword = "4117c86a";
    
    String connectionUrl = "jdbc:sqlserver://hlp7g46bh1.database.windows.net:1433;databaseName=jujsandhu_db"; 
    String username = "ee@hlp7g46bh1";
    String pword = "hello10??";
	public ResultSet getResults(String SQL){
         try {
        	//Class.forName("com.mysql.jdbc.Driver");
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection(connectionUrl,username,pword);
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
         }           
 
         catch (Exception e) {
            e.printStackTrace();
         }
         
         return rs;
	}
	
	public void insertToDatabase(String SQL){
		try {
        	//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection(connectionUrl,username,pword);
            
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
         }           
 
         catch (Exception e) {
            e.printStackTrace();
         }
	}
	
	public void closeConnection(){
		if (rs != null) try { rs.close(); } catch(Exception e) {}
        if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        if (con != null) try { con.close(); } catch(Exception e) {}
	}
}