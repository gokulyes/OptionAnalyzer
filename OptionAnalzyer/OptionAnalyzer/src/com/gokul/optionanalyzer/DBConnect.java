package com.gokul.optionanalyzer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnect {
	
	public static Connection connection = null;
	
	public static Connection getDBConnection() {
		
		try {
			
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			JOptionPane.showMessageDialog(null, "Database Connected.");
			return connection;
		} catch (ClassNotFoundException | SQLException e1) {
			
			JOptionPane.showMessageDialog(null, "Error connection database");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}			
	}

}
