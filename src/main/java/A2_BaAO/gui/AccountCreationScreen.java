package A2_BaAO.gui;

import A2_BaAO.dao.*;
import A2_BaAO.dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountCreationScreen extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton createAccountButton;
    private CustomerDAO customerDAO;

    public AccountCreationScreen(CustomerDAO customerDAO) {
        super("Create Account");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this window on close
        setSize(480, 240);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(5, 2));

        this.customerDAO = customerDAO;

        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel);

        nameField = new JTextField();
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        add(emailLabel);

        usernameField = new JTextField();
        add(usernameField);

        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);

        emailField = new JTextField();
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                if (validateInput(name, username, email, password)) {
                    CustomerDTO customer = new CustomerDTO(0, name, username, email, password); // Assuming customer ID is generated automatically
                    customerDAO.insert(customer); // Use the initialized customerDAO field
                    JOptionPane.showMessageDialog(AccountCreationScreen.this, "Account created successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AccountCreationScreen.this, "Invalid input. Please try again.");
                }
            }
        });
        add(createAccountButton);

        setVisible(true);
    }

    // Dummy validation method, replace this with your actual validation logic
    private boolean validateInput(String name, String username, String email, String password) {
        return !name.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty();
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

                    // Create the CustomerDAO instance and pass it to the AccountCreationScreen constructor
                    CustomerDAO customerDAO = new CustomerDAOImpl(connection);
                    new AccountCreationScreen(customerDAO);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQLException
                }
            }
        });
    }
}