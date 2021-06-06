import java.io.*;
import java.util.*;

public class LoadExport{
    private String[] TAGS = {"EXPENSE_TYPE_LIST", "EXPENSE_TYPE", "TYPE", "CODE", "DESCR", "EMPLOYEE_LIST", "EMPLOYEE", "SURNAME", "FIRST_NAME", "MAX_MONTHLY_VAL",
                             "EXPENSE_LIST", "EXPENSE", "EMPLOEE_CODE", "EXPENSE_CODE", "VAL", "TRN_LIST", "TRN", }
    private LoadExport(){

    }

    private static ArrayList<ExpenseType> expenseTypes;
    private static ArrayList<Employee> employees;
    private static HashMap<Employee, EmployeeExpense> employeesExpenses;
    private static HashMap<Employee, Transaction> employeesTransactions;

    public static ArrayList<ExpenseType> getExpenseTypes() {
        return expenseTypes;
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static HashMap<Employee, EmployeeExpense> getEmployeesExpenses() {
        return employeesExpenses;
    }

    public static HashMap<Employee, Transaction> getEmployeesTransactions() {
        return employeesTransactions;
    }

    public static void ExportData(HashMap<Employee, EmployeeExpense> e, HashMap<Employee, Transaction> t){
        String exp = System.getProperty("user.home") + "/Desktop" + "/EmployeesExpenses.txt";
        String trn = System.getProperty("user.home") + "/Desktop" + "/EmployeesTransactions.txt";

        try{
            ExportExpenses(e, exp);
            ExportTransactions(t, trn);
        }
        catch(Exception fnfe){
            System.out.println("!!!ERROR WHILE SAVING DATA!!!");
        }
    }

    public static void LoadData(String expTypePath, String empListPath, String empExpPath, String empTrnPath){
        expenseTypes            = loadExpenseTypes(expTypePath);
        employees               = loadEmployees(empListPath);
        employeesExpenses       = loadEmpExpenses(empExpPath);
        employeesTransactions   = loadEmpTransactions(empTrnPath);
        System.out.println("Data loading completed successfully!");
    }

    public static ArrayList<ExpenseType> loadExpenseTypes(String path){

        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<ExpenseType> et = new ArrayList<>();
        String sCurrentLine, type = null, code = null , descr = null;
        boolean hasType = false, hasCode = false, hasDescr = false;
        double maxMonValue = 0.0;

        try{
            fr = new FileReader(path);
            br = new BufferedReader(fr);
        }
        catch (Exception ioe){
            System.out.println("!!!PROBLEM ON FILE READING!!!");
        }

        try{
            while ((sCurrentLine = br.readLine()) != null){
                while(sCurrentLine.trim().isEmpty()) {
                    sCurrentLine = br.readLine(); //skip blank lines
                }
                if(sCurrentLine.trim().equalsIgnoreCase("EXPENSE_TYPE_LIST")) {
                    sCurrentLine = br.readLine();

                    while(sCurrentLine.trim().isEmpty()) {
                        sCurrentLine = br.readLine(); //skip blank lines
                    }
                    if (sCurrentLine.trim().equals("{")) {
                        while (!sCurrentLine.trim().equals("}") && sCurrentLine != null) {
                            sCurrentLine = br.readLine();
                            while (sCurrentLine.trim().isEmpty()) {
                                sCurrentLine = br.readLine(); //skip blank lines
                            }
                            if (sCurrentLine.trim().equalsIgnoreCase("EXPENSE_TYPE")) {
                                hasCode = hasDescr = hasType = false;
                                sCurrentLine = br.readLine();
                                while (sCurrentLine.trim().isEmpty()) {
                                    sCurrentLine = br.readLine(); //skip blank lines
                                }
                                if (sCurrentLine.equals("{")) {
                                    br.mark(10000);

                                    while (!sCurrentLine.equals("}") && sCurrentLine != null) {
                                        sCurrentLine = br.readLine();
                                        if (sCurrentLine.matches("(?i)\\s*code\\s*(\\S+\\s*)*")) {
                                            hasCode = true;
                                        } else if (sCurrentLine.matches("(?i)\\s*descr\\s*(\\S+\\s*)*")) {
                                            hasDescr = true;
                                        } else if (sCurrentLine.matches("(?i)\\s*type\\s*\\S+\\s*")) {
                                            hasType = true;
                                            type = sCurrentLine.trim().substring(5).trim();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){

        }
        return et;
    }

    private static ArrayList<Employee> loadEmployees(String empListPath) {
    }

    private static HashMap<Employee,EmployeeExpense> loadEmpExpenses(String empExpPath) {
    }

    private static HashMap<Employee,Transaction> loadEmpTransactions(String empTrnPath) {
    }

    private static void ExportExpenses(HashMap<Employee,EmployeeExpense> e, String exp) {
    }

    private static void ExportTransactions(HashMap<Employee,Transaction> t, String trn) {
    }
}