package A2_BaAO.gui;

import A2_BaAO.dao.*;
import A2_BaAO.dto.*;

import java.sql.Connection;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginScreen extends JFrame {
    private Connection connection = null; // Initialize connection to null
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private CustomerDAO customerDAO;

    public LoginScreen(CustomerDAO customerDAO, Connection connection) {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 240);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(3, 2));

        this.customerDAO = customerDAO; // Initialize customerDAO
        if (connection != null) {
            this.connection = connection; // Initialize connection if not null
        }

        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Perform login authentication here
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login successful");
                    // Open the main application window here
                    // For now, let's just close the login screen
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password");
                }
            }
        });
        add(loginButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the account creation screen
                new AccountCreationScreen(customerDAO);
                // Close the login screen
                dispose();
            }
        });
        add(createAccountButton);

        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        List<CustomerDTO> customers = customerDAO.getAll();
        for (CustomerDTO customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Construct the JDBC URL
                    String url = "jdbc:sqlite:src/main/resources/bankdb.db";

                    // Create a connection to the database
                    Connection connection = DriverManager.getConnection(url);

                    // Create the CustomerDAO instance and pass it to the LoginScreen constructor
                    CustomerDAO customerDAO = new CustomerDAOImpl(connection);
                    new LoginScreen(customerDAO, connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQLException
                }
            }
        });
    }
}