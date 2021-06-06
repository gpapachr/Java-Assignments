public class ExpenseType1 extends ExpenseType{
    private final double costPerUnit;

    private final String unit;

    ExpenseType1(String description, double maxMonthlyValue, double costPerUnit, String unit){
        super(description, maxMonthlyValue);
        this.costPerUnit = costPerUnit;
        this.unit = unit;
    }

    ExpenseType1(String code, String description, double maxMonthlyValue, double costPerUnit, String unit){
        super(code, description, maxMonthlyValue);
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

    @Override
    public String getType(){
        return "1";
    }
}
