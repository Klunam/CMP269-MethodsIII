package Assigments.Excersice1;

public class MealPlan extends PaymentMethod {

    public MealPlan(double balance){
        super("", balance);
        this.balance = balance;
    }

    public MealPlan(String accountHolder, double balance, double amount){
        super(accountHolder, balance);
        this.balance = balance;
    }

    @Override
    public void validateAccount(){
        if(this.balance < 0 ){
            System.out.println("Warning: Negative balance!" );

        } else {
            System.out.println("All good.");

        }
    }

    @Override
    public void processPayment(double amount){

        if(balance >= amount){
            balance = balance - amount;
            totalTransactions++;
            System.out.print("Success!");

        } else{
            System.out.println("Decline");
        }


    }

    @Override
    public String getPaymentStatus(){
        return "Balance: " + this.balance;
    }


}