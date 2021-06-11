public class PrePaymentTransaction extends Transaction{
    private final String type = "PREPAYMENT";
    PrePaymentTransaction(Employee employee, double costToPay){
        super(employee, costToPay);
    }

    @Override
    public String toString(){
        return Double.toString(costToPay) + "\t" + type;
    }
}
