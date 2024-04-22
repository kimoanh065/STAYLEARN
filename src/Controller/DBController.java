package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
	
	public Connection getConnection() {
		Connection conn = null;
		try{
			   String userName = "root";
			   String password = "W@2915djkq#";
			   String url = "jdbc:mysql://localhost/staylearn";
			   Class.forName ("com.mysql.cj.jdbc.Driver");
			   conn = DriverManager.getConnection(url, userName, password);
			   conn.setAutoCommit(true);
		} catch(Exception e){
			   System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = null;
		try {
			   Class.forName ("com.mysql.cj.jdbc.Driver");
			   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","W@2915djkq#");
			   System.out.println("Connect thanh cong");
			   
		} catch(Exception ignored){
		}
		
	}

}
