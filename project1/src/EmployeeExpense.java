public class EmployeeExpense {
    private final Employee employee;
    private final ExpenseType expenseType;
    private final double value;
    private final String reasoning;

    EmployeeExpense(Employee employee, ExpenseType expenseType, double value, String reasoning) {
        this.employee = employee;
        this.expenseType = expenseType;
        this.value = value;
        this.reasoning = reasoning;
    }

    public Employee getEmploee() {
        return employee;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public double getValue() {
        return value;
    }

    public String getReasoning() {
        return reasoning;
    }

    @Override
    public String toString(){
        return expenseType.getDescription() + "\t" + Double.toString(value);
    }
}
