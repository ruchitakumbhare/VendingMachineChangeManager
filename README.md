<b>Vending Machine Change Manager </b>
<p>This is a sample JAVA application for change(coins) manager of vending machine. </p>

Project skeleton used :
- Java 11
- JUnit 5

Features :
- Initialise the vending machine to a known state, for use when the machine is
set up. This should include setting the initial float (the coins placed in the
machine for customer change) which should be accepted as a parameter.
- Register coins that have been deposited by a user.
- Return the correct change to a user as coins when an order is received (a
parameter for the value of a product) and remove the coins from the
machine.
- solution should be a command line program.

Assumptions made :

- supported coins denomination is [5 pence, 10 pence, 20 pence, 50 pence, 1 pound, 2 pound]
- Product-value is taken from user as a parameter

Files structure : 

- src/main/java : Main class is the test-harness to test all the coins related functions.
- test/java : includes all unit tests

Instructions to run the project :

- Clone or download this project and run in IDE 
- Add coins in inventory (For the first time)
- Enter product-value
- Add coins user is going to deposit
- It returns correct change and dispensed coins details for valid order.
