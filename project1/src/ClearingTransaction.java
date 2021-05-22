public class ClearingTransaction extends Transaction{
    private final ExpenseType expenseType;

    ClearingTransaction(Employee employee, double costToPay, ExpenseType expenseType){
        super(employee, costToPay);
        this.expenseType = expenseType;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }
}
