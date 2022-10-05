import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/hpuas";
	final String USER = "root";
	final String PASS = "";
	
	Connection conn;
	Statement stat;
	ResultSet rs;
	ResultSetMetaData rsm;
	
	public Connect() {
		try {
			
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			stat=conn.createStatement();
			
			System.out.println("Connect berhasil");
		} catch (Exception e) {
			System.out.println("Connect gagal");
			e.printStackTrace();
		}
	}
}
