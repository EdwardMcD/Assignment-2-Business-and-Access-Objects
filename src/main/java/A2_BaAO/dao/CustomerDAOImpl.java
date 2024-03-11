package A2_BaAO.dao;

import A2_BaAO.dao.CustomerDAO;
import A2_BaAO.dto.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection;

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CustomerDTO get(int customerId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customerId = ?")) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
        return null; // If customer not found
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {
            while (resultSet.next()) {

                customers.add(extractCustomerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
        return customers;
    }

    @Override
    public void insert(CustomerDTO customer) {

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (customerId, name, email) VALUES (?, ?, ?)")) {
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void update(CustomerDTO customer) {

        try (PreparedStatement statement = connection.prepareStatement("UPDATE customers SET name = ?, email = ? WHERE customerId = ?")) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setInt(3, customer.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    @Override
    public void delete(CustomerDTO customer) {

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customerId = ?")) {
            statement.setInt(1, customer.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL Exception
        }
    }

    private CustomerDTO extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("customerId");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

        return new CustomerDTO(customerId, name, email, password);
    }
}