package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    /* Equivalence classes for withdraw(amount):
       - amount < 0 : negative input (should not change balance)
       - amount == 0 : zero (balance unchanged)
       - 0 < amount < balance : valid withdrawal (balance decreases by amount)
       - amount == balance : withdraw all (balance becomes 0)
       Edge cases: fractional amounts and precision
    */

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void withdrawZeroTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(0);
        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawEqualToBalanceTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(200);
        assertEquals(0, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawNegativeAmountTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(-50);
        // per spec, negative withdrawals should not change the balance
        assertEquals(200, bankAccount.getBalance(), 0.001);
    }


    @Test
    void withdrawFractionalAmountTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200.50);
        bankAccount.withdraw(0.50);
        assertEquals(200.00, bankAccount.getBalance(), 0.001);
    }



    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("gmail.com@")); //domain before @ symbol
        assertFalse(BankAccount.isEmailValid("@johndoe"));  // prefix after @ symbol
        assertFalse(BankAccount.isEmailValid("johndoegmail.com")); // missing @ symbol
        assertFalse(BankAccount.isEmailValid("john@.com"));      // missing domain name
        assertFalse(BankAccount.isEmailValid("john@gmailcom"));   // missing dot in domain name        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    

    
}