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
        }else{
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }else{
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
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

        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is invalid");
        }

        if(amount < 0){
            return;
        }
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
     * @throws IllegalArgumentException if amount is negative or has more than two decimal places
     */

    public static boolean isAmountValid(double amount) throws IllegalArgumentException{
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be a positive number");
        }
        String amountString = Double.toString(amount);
        int indexOfDecimal = amountString.indexOf('.');
        if (indexOfDecimal != -1){
            int decimalPlaces = amountString.length() - indexOfDecimal - 1;
            if (decimalPlaces > 2){
                throw new IllegalArgumentException("Amount cannot have more than two decimal places");
            }
            else {
                return true;
            }
        }
        return true;
    }

    /**
     * @post Will add the amount to the balance if the amount is valid.
     * @throws IllegalArgumentException if amount is negative or has more than two decimal places
     */

    public void deposit(double amount) throws IllegalArgumentException{
        
    }


    /**
     * @post will transfer amount from this account to the other account if amount is valid and this account has sufficient funds.
     * @throws InsufficientFundsException if amount is greater than balance
     * @throws IllegalArgumentException if amount is negative or has more than two decimal places
     */ 
    public void transfer(BankAccount other, double amount) throws InsufficientFundsException, IllegalArgumentException{
    
    }

}