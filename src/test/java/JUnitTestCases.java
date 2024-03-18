/*************************************************
 File: JUnitTestCases.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: JUnit test cases for Assignment 2
 *************************************************/

import A2_BaAO.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import A2_BaAO.dao.CustomerDAO;
import A2_BaAO.dao.CustomerDAOImpl;
import A2_BaAO.dto.CustomerDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;

public class JUnitTestCases {

    private Customer customer;
    private Account account;
    private Transaction transaction;

    @Before
    public void setUp() {
        // Initialize objects for testing
        customer = new Customer(1, "John Doe", "JDoe", "john@example.com", "password", new Date());
        account = new Account(1, 1000.0, "Savings", customer);
        transaction = new Transaction(1, 1, 500.0, "Deposit", new Date());
    }

    @Test
    public void testCustomerAttributes() {
        assertEquals(1, customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("password", customer.getPassword());
        assertNotNull(customer.getCreationDate());
    }

    @Test
    public void testAccountCreation() {
        assertEquals(1, account.getAccountId());
        assertEquals(1000.0, account.getBalance(), 0.001);
        assertEquals("Savings", account.getType());
    }

    @Test
    public void testTransactionCreation() {
        assertEquals(1, transaction.getTransactionId());
        assertEquals(1, transaction.getAccountId());
        assertEquals(500.0, transaction.getAmount(), 0.001);
        assertEquals("Deposit", transaction.getType());
        assertNotNull(transaction.getTimestamp());
    }

    @Test
    public void testProcessTransaction() {
        account.processTransaction(customer, transaction);
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    public void testAddAccountToCustomer() {
        Customer newCustomer = new Customer(2, "Jane Smith", "JSmith", "jane@example.com", "password", new Date());
        Account newAccount = new Account(2, 2000.0, "Checking", newCustomer);

        newCustomer.addAccount(newAccount);
        assertEquals(1, newCustomer.getAccounts().size());
    }

    @Test
    public void testWithdrawalTransaction() {
        Transaction withdrawalTransaction = new Transaction(2, 1, 200.0, "Withdrawal", new Date());
        account.processTransaction(customer, withdrawalTransaction);
        assertEquals(800.0, account.getBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawalWithInsufficientFunds() {
        Transaction withdrawalTransaction = new Transaction(3, 1, 1200.0, "Withdrawal", new Date());
        account.processTransaction(customer, withdrawalTransaction);
    }

    @Test
    public void testTransactionComparator() {
        Transaction t1 = new Transaction(1, 1, 500.0, "Deposit", new Date());
        Transaction t2 = new Transaction(2, 1, 1000.0, "Deposit", new Date());
        assertTrue(t1.compareTo(t2) < 0); // t1 amount is less than t2 amount
    }

    @Test
    public void testCustomerAddMultipleAccounts() {
        Customer customer = new Customer(1, "John Doe", "JDoe", "john@example.com", "password", new Date());

        // Create multiple accounts
        Account account1 = new Account(1, 1000.0, "Savings", customer);
        Account account2 = new Account(2, 2000.0, "Checking", customer);

        // Add accounts to the customer
        customer.addAccount(account1);
        customer.addAccount(account2);

        // Check if both accounts are associated with the customer
        assertEquals(2, customer.getAccounts().size());
        assertTrue(customer.getAccounts().contains(account1));
        assertTrue(customer.getAccounts().contains(account2));
    }

    @Test
    public void testChangeCustomerPassword() {
        String newPassword = "newpassword";
        customer.setPassword(newPassword);
        assertEquals(newPassword, customer.getPassword());
    }

    @Test
    public void testCompareCustomersBasedOnCreationDate() {
        // Create the first customer with the current date
        Customer customer = new Customer(1, "John Doe", "JDoe", "john@example.com", "password", new Date());

        // Pause
        try {
            Thread.sleep(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create the second customer with a later date
        Date laterDate = new Date();
        Customer newerCustomer = new Customer(2, "Jane Doe", "JDoe", "jane@example.com", "password", laterDate);

        // Assert that the first customer's creation date is earlier than the second customer's
        assertTrue(customer.compareTo(newerCustomer) < 0);
    }

    @Test
    public void testRemoveAccountFromCustomer() {
        customer.addAccount(account);
        customer.getAccounts().remove(account);
        assertEquals(0, customer.getAccounts().size());
    }

    @Test
    public void testUpdateCustomerAttributes() {
        String newName = "Jane Doe";
        String newEmail = "jane@example.com";
        customer.setName(newName);
        customer.setEmail(newEmail);
        assertEquals(newName, customer.getName());
        assertEquals(newEmail, customer.getEmail());
    }

    @Test
    public void testDepositTransaction() {
        Transaction depositTransaction = new Transaction(2, 1, 200.0, "Deposit", new Date());
        account.processTransaction(customer, depositTransaction);
        assertEquals(1200.0, account.getBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTransactionType() {
        Transaction invalidTransaction = new Transaction(3, 1, 100.0, "InvalidType", new Date());
        account.processTransaction(customer, invalidTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawalWithNegativeAmount() {
        Transaction negativeWithdrawal = new Transaction(4, 1, -100.0, "Withdrawal", new Date());
        account.processTransaction(customer, negativeWithdrawal);
    }

    @Test
    public void testUpdateAccountType() {
        String newType = "Checking";
        account.setType(newType);
        assertEquals(newType, account.getType());
    }

    @Test
    public void testCompareAccountsBasedOnBalance() {
        Account secondAccount = new Account(2, 2000.0, "Checking", customer);
        assertTrue(account.compareTo(secondAccount) < 0);
    }

    @Test
    public void testTransactionAmountSetter() {
        double newAmount = 700.0;
        transaction.setAmount(newAmount);
        assertEquals(newAmount, transaction.getAmount(), 0.001);
    }

    @Test
    public void testTransactionTypeSetter() {
        String newType = "Withdrawal";
        transaction.setType(newType);
        assertEquals(newType, transaction.getType());
    }

    @Test
    public void testTransactionTimestampSetter() {
        Date newTimestamp = new Date();
        transaction.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, transaction.getTimestamp());
    }

    @Test
    public void testCompareTransactionsBasedOnAmount() {
        Transaction secondTransaction = new Transaction(2, 1, 700.0, "Deposit", new Date());
        assertTrue(transaction.compareTo(secondTransaction) < 0);
    }

    private static Connection connection;
    private static CustomerDAO customerDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Connect to the SQLite test database
        String url = "jdbc:sqlite:bankdb.db";
        connection = DriverManager.getConnection(url);

        // Initialize the DAO with the test database connection
        customerDAO = new CustomerDAOImpl(connection);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Close the database connection after all tests are finished
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testJsonConversion() {
        String jsonString = "{\"name\":\"John Doe\",\"email\":\"jdoe@sfsu.edu\"}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert
            Customer customer = objectMapper.readValue(jsonString, Customer.class);
            assertEquals("John Doe", customer.getName());
            assertEquals("jdoe@sfsu.edu", customer.getEmail());

            // Convert object to JSON
            String convertedJsonString = objectMapper.writeValueAsString(customer);
            String expectedJsonString = "{\"customerId\":0,\"name\":\"John Doe\",\"email\":\"jdoe@sfsu.edu\",\"password\":null,\"accounts\":null,\"creationDate\":null}";

            // Compare JSON
            assertEquals(expectedJsonString, convertedJsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}