package proiectPao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proiectPao.databaseConnection.ConnectionUtils;

public class CheckDataBase {

	 public static void main(String[] args) throws ClassNotFoundException,
     SQLException {

 // Get Connection
 Connection connection = ConnectionUtils.getMyConnection();

 // Create statement
 Statement statement = connection.createStatement();

 String sql = "Select employee_id from employees";

 // Execute SQL statement returns a ResultSet object.
 ResultSet rs = statement.executeQuery(sql);

 // Fetch on the ResultSet        
 // Move the cursor to the next record.
 		while (rs.next()) {
 			int empId = rs.getInt(1);
     		System.out.println("EmpId:" + empId);
 		}

 		// Close connection.
 		connection.close();
	 }
}	
