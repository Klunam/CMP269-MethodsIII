package Assigments.Excersice1;
/* Fields: * protected String accountHolder
protected double balance

Constructor: Initialize both fields.

Static Member: Add a static int totalTransactions to keep track of how many payments have been processed across the entire system.
Abstract Method: abstract void validateAccount();
*/


public abstract class PaymentMethod implements Payable{
    protected String accountHolder;
    protected double balance;

    public PaymentMethod(String accountHolder, double balance){
        this.accountHolder = accountHolder;
        this.balance = balance;
    }


    static int totalTransactions = 0;
    // future note does the order of this^^ affect the progra, long run?
    

    abstract void validateAccount();


}