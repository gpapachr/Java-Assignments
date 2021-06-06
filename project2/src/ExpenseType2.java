public class ExpenseType2 extends ExpenseType{
    private final double percentage;
    private final String type = "2";

    ExpenseType2(String description, double maxMonthlyValue, double percentage){
        super(description, maxMonthlyValue);
        this.percentage = percentage;
    }

    ExpenseType2(String code, String description, double maxMonthlyValue, double percentage){
        super(code, description, maxMonthlyValue);
        this.percentage = percentage;
    }

    public double getPercentage(){
        return percentage;
    }

    @Override
    public double calculateCost(double value){
        return percentage*value;
    }

    @Override
    public String getType(){
        return "2";
    }
}
