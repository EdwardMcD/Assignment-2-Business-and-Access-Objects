package A2_BaAO.dao;

import A2_BaAO.dto.AccountDTO;
import java.util.List;

public interface AccountDAO {
    AccountDTO get(int accountId);
    List<AccountDTO> getAll();
    void insert(AccountDTO account);
    void update(AccountDTO account);
    void delete(AccountDTO account);
}