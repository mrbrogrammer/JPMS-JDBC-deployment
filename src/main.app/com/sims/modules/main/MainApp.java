package com.sims.modules.main;
import java.util.*;
import java.io.*;
import java.sql.*;
import com.sims.modules.hello.HelloModules;
import com.sims.modules.hello.HelloInterface;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/data";
        String username = "admin";
        String password = "mypass";
    
        String query = "SELECT * FROM COMPANY";
        String insertQuery = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES " +
                    "(5, 'Simon', 28, 'Camps Bay', 30000.00)";
        
        String updateQuery = "UPDATE COMPANY SET NAME = ? WHERE NAME = 'Tom' ";
        
        Connection connection = null;
    
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established successfully");
            connection.setAutoCommit(true); // what is this!
            
            /**
             * @params updateQuery
             * create a PreparedStatement object. Then update row where name is Tom
             * to SIMS.
             * */
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setString(1, "SIMS");
            pstmt.executeUpdate();
            
            Statement stmt = connection.createStatement();
            stmt.addBatch(insertQuery);
            
            stmt.clearBatch();
            stmt.executeBatch();
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                String address = rs.getString("ADDRESS").toString();
                String salary = String.format("%.02f" , rs.getFloat("SALARY"));
                System.out.println(id + ": " + name + ", " + age + ", " + address.trim() + ", " + "R" + salary);
            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
            System.out.println("Connection Closed....");
        }
    }
}
