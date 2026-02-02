package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    /* Equivalence classes for getBalance():
       - initial positive balance
       - initial zero balance
       - after valid withdrawal (balance decreased accordingly)
       - after failed withdrawal (balance unchanged)
       - fractional amounts / precision
       - after multiple operations (sequence)
    */

    @Test
    void getBalanceInitialZeroTest() {
        // EC: initial balance is zero
        BankAccount bankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance(), 0.001);
    }

    @Test
    void getBalanceAfterValidWithdrawTest() throws InsufficientFundsException {
        // EC: valid withdrawal reduces balance
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(50);
        assertEquals(150, bankAccount.getBalance(), 0.001);
    }

    @Test
    void getBalanceAfterFailedWithdrawTest() {
        // EC: failed withdraw (overdraw) leaves balance unchanged
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void getBalancePrecisionTest() throws InsufficientFundsException {
        // EC: precision with fractional amounts
        BankAccount bankAccount = new BankAccount("a@b.com", 100.125);
        bankAccount.withdraw(0.125);
        assertEquals(100.0, bankAccount.getBalance(), 0.001);
    }

    @Test
    void getBalanceAfterMultipleWithdrawsTest() throws InsufficientFundsException {
        // EC: sequence of operations
        BankAccount bankAccount = new BankAccount("a@b.com", 500);
        bankAccount.withdraw(100);
        bankAccount.withdraw(50);
        assertEquals(350, bankAccount.getBalance(), 0.001);
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

    @Test 
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(100)); // valid amount equivalence class showing integer will work and is boolean test
        assertTrue(BankAccount.isAmountValid(0)); // zero amount showing boundary case of valid amount which is boolean test
        assertTrue(BankAccount.isAmountValid(99.99)); // valid amount with two decimal places showing a middle boundary case where we just check a double which is a boolean test
        assertTrue(BankAccount.isAmountValid(10.1)); // valid amount with one decimal place showing another boundary of a value with one decimal 
        assertFalse(BankAccount.isAmountValid(-50)); // negative amount showing invalid equivalence class for negative values
        assertFalse(BankAccount.isAmountValid(100.999)); // more than two decimal places showing invalid equivalence class for values with more than two decimal places
    }
    

    
}