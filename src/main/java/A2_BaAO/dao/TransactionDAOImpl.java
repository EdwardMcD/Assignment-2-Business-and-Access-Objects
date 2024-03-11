package A2_BaAO.dao;

import A2_BaAO.dto.TransactionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private Connection connection;

    public TransactionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransactionDTO get(int transactionId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions WHERE transactionId = ?")) {
            statement.setInt(1, transactionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return extractTransactionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
        return null; // If transaction not found
    }

    @Override
    public List<TransactionDTO> getAll() {
        List<TransactionDTO> transactions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                transactions.add(extractTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
        return transactions;
    }

    @Override
    public void insert(TransactionDTO transaction) {

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO transactions (transactionId, accountId, amount, type) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, transaction.getTransactionId());
            statement.setInt(2, transaction.getAccountId());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void update(TransactionDTO transaction) {

        try (PreparedStatement statement = connection.prepareStatement("UPDATE transactions SET accountId = ?, amount = ?, type = ? WHERE transactionId = ?")) {
            statement.setInt(1, transaction.getAccountId());
            statement.setDouble(2, transaction.getAmount());
            statement.setString(3, transaction.getType());
            statement.setInt(4, transaction.getTransactionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void delete(TransactionDTO transaction) {

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM transactions WHERE transactionId = ?")) {
            statement.setInt(1, transaction.getTransactionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    // Helper method to extract transaction details from ResultSet
    private TransactionDTO extractTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        int transactionId = resultSet.getInt("transactionId");
        int accountId = resultSet.getInt("accountId");
        double amount = resultSet.getDouble("amount");
        String type = resultSet.getString("type");

        return new TransactionDTO(transactionId, accountId, amount, type);
    }
}