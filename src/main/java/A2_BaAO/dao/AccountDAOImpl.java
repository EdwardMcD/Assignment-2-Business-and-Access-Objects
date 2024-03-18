/*************************************************
 File: AccountDAOImpl.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: Implementation of the Account DAO
 *************************************************/

package A2_BaAO.dao;

import A2_BaAO.dto.AccountDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private Connection connection;

    public AccountDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public AccountDTO get(int accountId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountId = ?")) {
            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extract account details from resultSet and create an AccountDTO object
                    return extractAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
        return null; // Return null if account not found
    }

    @Override
    public List<AccountDTO> getAll() {
        List<AccountDTO> accounts = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts")) {
            while (resultSet.next()) {
                // Extract account details from resultSet and create AccountDTO objects
                accounts.add(extractAccountFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
        return accounts;
    }

    @Override
    public void insert(AccountDTO account) {
        // Implement the insert method to insert a new account into the database
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts (accountId, balance, type, customerId) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, account.getAccountId());
            statement.setDouble(2, account.getBalance());
            statement.setString(3, account.getType());
            statement.setInt(4, account.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void update(AccountDTO account) {
        // Implement the update method to update an existing account in the database
        try (PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ?, type = ?, customerId = ? WHERE accountId = ?")) {
            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getType());
            statement.setInt(3, account.getCustomerId());
            statement.setInt(4, account.getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void delete(AccountDTO account) {
        // Implement the delete method to delete an account from the database
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM accounts WHERE accountId = ?")) {
            statement.setInt(1, account.getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    // Helper method to extract account details from ResultSet
    private AccountDTO extractAccountFromResultSet(ResultSet resultSet) throws SQLException {
        int accountId = resultSet.getInt("accountId");
        double balance = resultSet.getDouble("balance");
        String type = resultSet.getString("type");
        int customerId = resultSet.getInt("customerId");

        return new AccountDTO(accountId, balance, type, customerId);
    }
}