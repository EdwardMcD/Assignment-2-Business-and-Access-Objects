/*************************************************
 File: Customer.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: Customer Business Object
 *************************************************/

package A2_BaAO;

import java.util.ArrayList;
import java.util.Date;

public class Customer implements Comparable<Customer> {
    // Attributes
    private int customerId;
    private String name;
    private String username;
    private String email;
    private String password;
    private ArrayList<Account> accounts;
    private Date creationDate;

    public Customer() {
        // Default Jackson constructor
    }

    public Customer(int customerId, String name, String username, String email, String password, Date creationDate) {
        this.customerId = customerId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.creationDate = creationDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(Customer other) {
        // Compare based on creation date
        return this.creationDate.compareTo(other.creationDate);
    }
}