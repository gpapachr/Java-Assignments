public class ClearingTransaction extends Transaction{
    private final ExpenseType expenseType;
    private final String type;

    ClearingTransaction(Employee employee, double costToPay, ExpenseType expenseType){
        super(employee, costToPay);
        this.expenseType = expenseType;
        this.type = "CLEARING";
    }

    ClearingTransaction(Employee employee, double costToPay){
        super(employee, costToPay);
        this.expenseType = null;
        this.type = "FINAL_CLEARING";
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
