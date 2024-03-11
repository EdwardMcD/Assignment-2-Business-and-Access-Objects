package A2_BaAO.dto;

public class AccountDTO {
    private int accountId;
    private double balance;
    private String type;
    private int customerId;

    public AccountDTO(int accountId, double balance, String type, int customerId) {
        this.accountId = accountId;
        this.balance = balance;
        this.type = type;
        this.customerId = customerId;
    }

    // Getter and setter methods
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}