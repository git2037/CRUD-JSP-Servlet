package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection getJDBCConnection() throws ClassNotFoundException, SQLException {
		// kết nối project với MySql
		final String url = "jdbc:mysql://localhost:3306/qlsv";
		final String user = "root";
		final String password = "At170234";

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);
	}

	public static void closeConnection(Connection con) {
		// đóng kết nối
		try {
			if (con != null) {
				con.close();
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}
