package Assigments.Excersice1;

public class CreditCard extends PaymentMethod {
    private double creditLimit;

    // Constructor
    public CreditCard(String accountHolder, double balance, double creditLimit) {
        /*super() method  to call constructor*/ 
        super(accountHolder, balance);
        this.creditLimit = creditLimit;
    }


    //Override process payaments
    @Override
    public void validateAccount() {
        System.out.println("Validating Credit Card for " + accountHolder + "...");
    }

    @Override
    public void processPayment(double amount) {
        // Check if amount exceeds balance + limit
        if (amount > (balance + creditLimit)) {
            System.out.println("Transaction Declined: Over credit limit.");
        } else {
            balance -= amount;
            totalTransactions++; 
            System.out.println("Credit Card Payment Processed: $" + amount);
        }
    }

    @Override
    public String getPaymentStatus() {
        if (balance < 0) {
            return "Status: Owing $" + Math.abs(balance);
        }
        return "Status: Positive Balance";
    }
}