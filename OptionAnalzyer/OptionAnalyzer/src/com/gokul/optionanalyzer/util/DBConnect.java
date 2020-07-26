package com.gokul.optionanalyzer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnect {
	
	public static Connection connection = null;
	
	public static Connection getDBConnection() {
		
		try {
			
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//			JOptionPane.showMessageDialog(null, "Database Connected.");
			return connection;
		} catch (ClassNotFoundException | SQLException e1) {
			
			JOptionPane.showMessageDialog(null, "Error connection database");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}			
	}
	
	public static ResultSet getTable(String strSQL) {
		
		try {

			checkDBConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(strSQL);
			return preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static int getRowCount(ResultSet set) throws SQLException
	{
	   int rowCount;
	   int currentRow = set.getRow();            // Get current row
	   rowCount = set.last() ? set.getRow() : 0; // Determine number of rows
	   if (currentRow == 0)                      // If there was no current row
	      set.beforeFirst();                     // We want next() to go to first row
	   else                                      // If there WAS a current row
	      set.absolute(currentRow);              // Restore it
	   return rowCount;
	}
	
	public static void checkDBConnection() { // Check if connected, if not connect to DB.
		
		if(connection == null) {
			connection = getDBConnection();
		}		
	}

}
