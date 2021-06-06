public class ClearingTransaction extends Transaction{
    private final ExpenseType expenseType;
    private final String type = "clearing";

    ClearingTransaction(Employee employee, double costToPay, ExpenseType expenseType){
        super(employee, costToPay);
        this.expenseType = expenseType;
    }

    @Override
    public ExpenseType getExpenseType() {
        return expenseType;
    }

    @Override
    public String toString(){
        return Double.toString(super.costToPay) + "\t" + expenseType.getDescription();
    }
}
