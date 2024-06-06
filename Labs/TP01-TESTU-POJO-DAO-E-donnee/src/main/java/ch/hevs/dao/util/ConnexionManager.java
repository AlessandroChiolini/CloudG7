package ch.hevs.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionManager {
	private static Connection conn;

	public static Connection getConnexion() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/DB");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;

	}
}
