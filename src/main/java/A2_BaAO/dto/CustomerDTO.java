/*************************************************
 File: CustomerDTO.java
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: DTO for Customer Business Object
 *************************************************/

package A2_BaAO.dto;

public class CustomerDTO {
    private int customerId;
    private String name;
    private String email;
    private String password;

    public CustomerDTO(int customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter and setter methods
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
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
}