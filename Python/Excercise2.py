# Excersice 2
# Kevin Mitre

class Payment:
    total_transactions = 0

    def __init__(self, transaction_id, amount):
        self.transaction_id = transaction_id
        self.amount = amount
        self.is_processed = False

    def process_payment(self):
        # FIX: Actually increment the counter here
        Payment.total_transactions += 1
        return f"Payment {self.transaction_id} for ${self.amount} is being initiated"

class CreditCard(Payment):
    def __init__(self, transaction_id, amount, card_number):
        super().__init__(transaction_id, amount)
        self.card_number = card_number

    def process_payment(self):
        # This calls the parent and adds credit card info
        base = super().process_payment()
        return f"{base} using Card: {self.card_number}"

# FIX: Added (Payment) here
class MealPlan(Payment):
    def __init__(self, transaction_id, amount, student_id):
        super().__init__(transaction_id, amount)
        self.student_id = student_id

    def process_payment(self):
        base_message = super().process_payment()
        # FIX: Removed card_number because it doesn't exist in this class
        return f"{base_message} using Meal Plan for Student: {self.student_id}."

class FinancialAid(Payment):
    def __init__(self, transaction_id, amount, aid_type):
        super().__init__(transaction_id, amount)
        self.aid_type = aid_type

    def process_payment(self):
        base_message = super().process_payment()
        return f"{base_message} via Financial Aid: {self.aid_type}."

if __name__ == "__main__":
    payment_queue = [
        CreditCard("TXN001", 50.0, "4444-5555"),
        MealPlan("TXN002", 12.50, "ID#9982"),
        FinancialAid("TXN003", 500.0, "PELL Grant")
    ]
    
    print("--- Lehman Campus Payment System ---")
    for p in payment_queue:
        print(p.process_payment())
        
    print(f"\nTotal System Transactions: {Payment.total_transactions}")