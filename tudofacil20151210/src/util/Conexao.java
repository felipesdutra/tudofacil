package util;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/nomebasededados", "root","aplicac@oAdm");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/tudofacil", "root","root");
		}catch (Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
		return conn;
		
	}
}


