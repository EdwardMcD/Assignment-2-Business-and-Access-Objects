package A2_BaAO.dao;

import A2_BaAO.dto.TransactionDTO;

import java.util.List;

public interface TransactionDAO {
    TransactionDTO get(int transactionId);
    List<TransactionDTO> getAll();
    void insert(TransactionDTO transaction);
    void update(TransactionDTO transaction);
    void delete(TransactionDTO transaction);
}
