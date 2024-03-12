/*************************************************
 File: README.md
 By: Edward McDonald
 Date: March 11th, 2024
 Compile: -
 Description: Description of Assignment 2
 *************************************************/

# Assignment-2-Business-and-Access-Objects
Assignment – 2 [ 200 Points ] CSC-413-02 Spring 2024 San Francisco State University Computer Science Department

Hello, this file is intended to be a roadmap in navigating and executing my submission for Assignment 2.
Github repo: https://github.com/EdwardMcD/Assignment-2-Business-and-Access-Objects


Directories:

.
├── README.md
├── bankdb.db
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── A2_BaAO
    │   │       ├── Account.java
    │   │       ├── Customer.java
    │   │       ├── PriorityQueue.java
    │   │       ├── Transaction.java
    │   │       ├── dao
    │   │       │   ├── AccountDAO.java
    │   │       │   ├── AccountDAOImpl.java
    │   │       │   ├── CustomerDAO.java
    │   │       │   ├── CustomerDAOImpl.java
    │   │       │   ├── TransactionDAO.java
    │   │       │   └── TransactionDAOImpl.java
    │   │       ├── dto
    │   │       │   ├── AccountDTO.java
    │   │       │   ├── CustomerDTO.java
    │   │       │   └── TransactionDTO.java
    │   │       └── main.java
    │   └── resources
    │       └── bankdb.db
    └── test
        └── java
            └── JUnitTestCases.java

.../Assignment-2-Business-and-Access-Objects/src/main/java/A2_BaAO/
This directory contains the main java files, as well as two subfolders dto/ and dao/ which contain the business object dtos and daos respectively.

.../Assignment-2-Business-and-Access-Objects/src/main/resources/
.../Assignment-2-Business-and-Access-Objects/
These directories contain banked.db. This is the database I will be using for this assignment. I am managing this database with SQLite.

.../Assignment-2-Business-and-Access-Objects/src/test/java/
This directory contains the JUnit test cases.

Requirements outline:

Business objects can be found in the .../Assignment-2-Business-and-Access-Objects/src/main/java/A2_BaAO/ directory. Per the professors in-class instruction, I have met the minimum requirement of 3 business objects:
	Customer
	Transaction
	Account

Customer
The Customer business object represents a customer with the following attributes:
	customerId: An integer representing the unique ID of the customer.
	name: A string representing the name of the customer.
	email: A string representing the email address of the customer.
	password: A string representing the password associated with the customer's account.
	accounts: An ArrayList of Account business objects representing the accounts owned by the customer. Being an ArrayList, it allows a customer to own multiple accounts.
	creationDate: A Date object representing the date when the customer's account was created.
This is also where you can find addAccount(), a method to add accounts to the customer.
	
Transaction
The Transaction business object represents a single transaction with the following attributes:
	transactionId: An integer representing the unique identifier of the transaction.
	accountId: An integer representing the unique identifier of the account associated with the transaction.
	amount: A double representing the amount of the transaction.
	type: A string representing the type of transaction (e.g., deposit, withdrawal).
	timestamp: A Date object representing the timestamp of the transaction.

Account
The Account business object represents a single account with the following attributes:
	accountId: An integer representing the unique identifier of the account.
	balance: A double representing the current balance of the account.
	type: A string representing the type of account (e.g., savings, checking).
	customer: A reference to the Customer object associated with the account.
This is also where you can find processTransaction(), a method that validates the transaction type, amount, and ensures that sufficient funds are available for withdrawals.

JUnit Test Cases
JUnit test cases can be found in the .../Assignment-2-Business-and-Access-Objects/src/test/java/ directory within JUnitTestCases.java. This is also where I implimented requirement 9 (JSON exercises):

    @Test
    public void testJsonConversion() {
        String jsonString = "{\"name\":\"John Doe\",\"email\":\"jdoe@sfsu.edu\"}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert
            Customer customer = objectMapper.readValue(jsonString, Customer.class);
            assertEquals("John Doe", customer.getName());
            assertEquals("jdoe@sfsu.edu", customer.getEmail());

            // Convert object to JSON
            String convertedJsonString = objectMapper.writeValueAsString(customer);
            String expectedJsonString = "{\"customerId\":0,\"name\":\"John Doe\",\"email\":\"jdoe@sfsu.edu\",\"password\":null,\"accounts\":null,\"creationDate\":null}";

            // Compare JSON
            assertEquals(expectedJsonString, convertedJsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

DTO and DAOs
My DTO and DAOs can be found in these two directories:
	.../Assignment-2-Business-and-Access-Objects/src/main/java/A2_BaAO/dto/
	.../Assignment-2-Business-and-Access-Objects/src/main/java/A2_BaAO/dao/