import java.util.ArrayList;

public class Employee {
    private String id;
    private final String lastname;
    private final String firstname;
    private final double maxMonthlyPayment;

    Employee(String lastname, String firstname, double maxMonthlyPayment){
        this.lastname = lastname;
        this.firstname = firstname;
        this.maxMonthlyPayment = maxMonthlyPayment;
    }

    Employee(String id, String lastname, String firstname, double maxMonthlyPayment){
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.maxMonthlyPayment = maxMonthlyPayment;
    }

    public String getId(){
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public double getMaxMonthlyPayment() {
        return maxMonthlyPayment;
    }

    @Override
    public String toString(){
        return lastname + "\t" + firstname;
    }
}
