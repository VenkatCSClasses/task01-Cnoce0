package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if amount is greater than balance
     * If the amount is negative the balance is not changed and will return nothing.
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        // Check if email contains '@'
        if (email.indexOf('@') == -1){
            return false;
        }

        // Check if email contains at least one '.' after '@'
        int atIndex = email.indexOf('@');
        if (email.indexOf('.', atIndex) == -1){
            return false;
        }

        // Check if email starts or ends with '.'
        if (email.startsWith(".") || email.endsWith(".")){
            return false;
        }

        // Check if email contains consecutive dots
        if (email.contains("..")){
            return false;
        }

        // Check if '@' is not the first or last character
        if (atIndex == 0 || atIndex == email.length() - 1){
            return false;
        }
         
        if (email.contains("@.") || email.contains(".@")){
            return false;
        }

        return true;
    }

    /**
     * @post Will take a positive amount with two decimals places or less and return false if the amount is invalid.
     */

    public static boolean isAmountValid(double amount){
        return false;
    }
}