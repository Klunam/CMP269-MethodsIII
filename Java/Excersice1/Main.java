package Assigments.Excersice1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //1.List
        ArrayList<Payable> paymentQueue = new ArrayList<>();

        
        //Add onr CreditCard object
        paymentQueue.add(new CreditCard("Kevin", 100.0, 500.0)); 
        //Add one Mealplan object
        paymentQueue.add(new MealPlan(75.0));

        // Loop throught the list and call processPayment(50.0)
        for (Payable p : paymentQueue) {
            p.processPayment(50.0);
        }

        //4. Print the final totalTrasactions
        System.out.println("Total System Transactions: " + PaymentMethod.totalTransactions);
    }
}