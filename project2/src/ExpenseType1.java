public class ExpenseType1 extends ExpenseType{
    private final double costPerUnit;

    private final String unit;

    ExpenseType1(String description, double maxMonthlyValue, double costPerUnit, String unit){
        super(description, maxMonthlyValue);
        this.costPerUnit = costPerUnit;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public double calculateCost(double value){
        return costPerUnit*value;
    }
}
