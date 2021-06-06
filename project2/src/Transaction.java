public class Transaction {
    private final Employee employee;
    protected final double costToPay;
    private final String type = "default";

    Transaction(Employee employee, double costToPay) {
        this.employee = employee;
        this.costToPay = costToPay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getCostToPay() {
        return costToPay;
    }

    @Override
    public String toString(){
        return Double.toString(costToPay) + "\t" + type;
    }

    public String getType(){
        return type;
    }

    public ExpenseType getExpenseType() {
        return null;
    }
}
