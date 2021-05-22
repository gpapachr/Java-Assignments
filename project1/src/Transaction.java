public class Transaction {
    private final Employee employee;
    private final double costToPay;

    Transaction(Employee employee, double costToPay){
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
        return Double.toString(costToPay);
    }
}
