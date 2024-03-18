/*************************************************
 File: main.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: Main method for Assignment 2, handles user interaction through the terminal
 *************************************************/

package A2_BaAO;
import A2_BaAO.dao.*;
import A2_BaAO.dto.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import A2_BaAO.gui.LoginScreen;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Construct the JDBC URL
                    String url = "jdbc:sqlite:src/main/resources/bankdb.db";

                    // Create a connection to the database
                    Connection connection = DriverManager.getConnection(url);

                    // Create the CustomerDAO instance
                    CustomerDAO customerDAO = new CustomerDAOImpl(connection);

                    // Create the login screen with the dependencies
                    new LoginScreen(customerDAO, connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQLException
                }
            }
        });
    }
}