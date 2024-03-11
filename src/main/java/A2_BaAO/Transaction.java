package A2_BaAO;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    // Attributes
    private int transactionId;
    private int accountId;
    private double amount;
    private String type;
    private Date timestamp;

    public Transaction() {
        // Default Jackson constructor
    }

    // Constructors
    public Transaction(int transactionId, int accountId, double amount, String type, Date timestamp) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Implementing compareTo method for Comparable interface
    @Override
    public int compareTo(Transaction other) {

        if (this.amount < other.amount) {
            return -1;
        } else if (this.amount > other.amount) {
            return 1;
        } else {
            return 0;
        }
    }
}