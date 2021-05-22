public class ExpenseType {
    private static int id = 0;
    private final String description;
    private final double maxMonthlyValue;

    ExpenseType(String description, double maxMonthlyValue){
        id++;
        this.description = description;
        this.maxMonthlyValue = maxMonthlyValue;
    }

    public static int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getMaxMonthlyValue() {
        return maxMonthlyValue;
    }

    public double calculateCost(double value){
        return -1;      // it will be overridden; -1 indicates that an error has occurred
    }

    @Override
    public String toString(){
        return id + "\t" + description + "\t" + maxMonthlyValue + "\n";
    }
}
