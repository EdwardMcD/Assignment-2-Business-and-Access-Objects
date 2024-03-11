package A2_BaAO.dao;

import A2_BaAO.dto.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    CustomerDTO get(int customerId);
    List<CustomerDTO> getAll();
    void insert(CustomerDTO customer);
    void update(CustomerDTO customer);
    void delete(CustomerDTO customer);
}