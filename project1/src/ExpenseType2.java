public class ExpenseType2 extends ExpenseType{
    private final double percentage;

    ExpenseType2(String description, double maxMonthlyValue, double percentage){
        super(description, maxMonthlyValue);
        this.percentage = percentage;
    }

    public double getPercentage(){
        return percentage;
    }

    @Override
    public double calculateCost(double value){
        return percentage*value;
    }
}
