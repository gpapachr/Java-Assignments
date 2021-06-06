import java.util.concurrent.atomic.AtomicLong;

public class ExpenseType {
    private String id;
    private final String description;
    private final double maxMonthlyValue;

    private static AtomicLong idCounter = new AtomicLong();

    protected static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

    ExpenseType(String description, double maxMonthlyValue){
        this.id = createID();
        this.description = description;
        this.maxMonthlyValue = maxMonthlyValue;
    }

    ExpenseType(String code, String descr, double maxMonthlyValue){
        this.id = code;
        this.description = descr;
        this.maxMonthlyValue = maxMonthlyValue;
    }

    public String getId() {
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

    public String getType(){
        return "0";
    }

    @Override
    public String toString(){
        return id + "\t" + description + "\t" + maxMonthlyValue;
    }
}
