package TPMovieSyeytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Open {
	
	static final String oracleDriver="oracle.jdbc.OracleDriver";
	static final String oracleURL="jdbc:oracle:thin:@localhost:1521:xe";
	static final String oracleId="movie";
	static final String oraclePw="movie";
	
	public Connection getConnection() {

		Connection con= null;
		
		try {
			Class.forName(oracleDriver);
			con = DriverManager.getConnection(oracleURL,oracleId,oraclePw);
		
		} catch (ClassNotFoundException e) {
			System.out.println("예외처리 1:"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외처리 2:"+e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
}









