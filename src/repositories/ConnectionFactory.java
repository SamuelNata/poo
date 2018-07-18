package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		boolean getConnection = false;
		while(!getConnection) {
        	try {
				return DriverManager.getConnection("jdbc:postgresql://localhost:5432/poo", "postgres", "123456");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
    }
}
