import java.io.*;
import java.util.*;

public class LoadExport{
    private static String[] TAGS = {"EXPENSE_TYPE_LIST", "EXPENSE_TYPE", "TYPE", "CODE", "DESCR", "EMPLOYEE_LIST", "EMPLOYEE", "SURNAME", "FIRST_NAME", "MAX_MONTHLY_VAL",
                             "EXPENSE_LIST", "EXPENSE", "EMPLOYEE_CODE", "EXPENSE_CODE", "VAL", "TRN_LIST", "TRN", "COST_PER_UNIT", "UNIT", "PERCENTAGE"};
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
        String checkpoint = null, sCurrentLine, type = null, code = null , descr = null;
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
                                        checkpoint = sCurrentLine = br.readLine();
                                        if (sCurrentLine.matches("(?i)\\s*code\\s*(\\S+\\s*)*")) {
                                            hasCode = true;
                                            code = sCurrentLine.trim().substring("CODE".length()).trim();
                                        } else if (sCurrentLine.matches("(?i)\\s*descr\\s*(\\S+\\s*)*")) {
                                            hasDescr = true;
                                            descr = sCurrentLine.trim().substring("EXPENSE_TYPE_DESCR".length()).trim();
                                        } else if (sCurrentLine.matches("(?i)\\s*type\\s*\\S+\\s*")) {
                                            hasType = true;
                                            type = sCurrentLine.trim().substring(5).trim();
                                        }
                                    }
                                }
                                if (hasCode && hasDescr && hasType){
                                    br.reset();
                                    sCurrentLine = br.readLine();
                                    if(type.trim().equalsIgnoreCase("1")){
                                        double costPerUnit = 0;
                                        String unit = null;
                                        while(!sCurrentLine.trim().equals("}")) {
                                            if (sCurrentLine.matches("(?i)\\s*cost_per_unit\\s*(\\S+\\s*)*")) {
                                                costPerUnit = Double.parseDouble(sCurrentLine.trim().substring("cost_per_unit".length()).trim());
                                            }
                                            else if (sCurrentLine.matches("(?i)\\s*unit\\s*(\\S+\\s*)*")) {
                                                unit = sCurrentLine.trim().substring("UNIT".length());
                                            }
                                            sCurrentLine = br.readLine();
                                        }
                                        et.add(new ExpenseType1(code, descr, maxMonValue, costPerUnit, unit));
                                    }
                                    else if(type.trim().equalsIgnoreCase("2")){
                                        double percentage = 0.0;
                                        if (sCurrentLine.matches("(?i)\\s*percentage\\s*(\\S+\\s*)*")) {
                                            percentage = Double.parseDouble(sCurrentLine.trim().substring("PERCENTAGE".length()).trim());
                                        }
                                        et.add(new ExpenseType2(code, descr, maxMonValue, percentage));
                                    }
                                }
                                else{
                                    System.out.println("File in line: ' " + checkpoint + " ' was skipped during loading due to insufficient data...");
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("!!!ERROR DURING EXPENSE_TYPES LOADING!!!");
            et = null;
        }
        return et;
    }

    private static ArrayList<Employee> loadEmployees(String empListPath) {
        return null;
    }

    private static HashMap<Employee,EmployeeExpense> loadEmpExpenses(String empExpPath) {
        return null;
    }

    private static HashMap<Employee,Transaction> loadEmpTransactions(String empTrnPath) {
        return null;
    }

    private static void ExportExpenses(HashMap<Employee,EmployeeExpense> e, String exp) {
        File file = new File (exp);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter (file);
            printWriter.println (TAGS[10]);
            printWriter.println ("{");
            for (Employee emp: e.keySet()){
                printWriter.println ("    " + TAGS[11]);
                printWriter.println ("    " +  "{");
                printWriter.println ("    " + "    " + TAGS[12] + "   " + emp.getId());
                printWriter.println ("    " + "    " + TAGS[13] + "   " + e.get(emp).getExpenseType().getType());
                printWriter.println ("    " + "    " + TAGS[14] + "   " + e.get(emp).getValue());
                printWriter.println ("    " + "}");
            }
            printWriter.println ("}");
            System.out.println("Export completed! File: " + file.getAbsolutePath());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private static void ExportTransactions(HashMap<Employee,Transaction> t, String trn) {
        File file = new File (trn);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter (file);
            printWriter.println (TAGS[15]);
            printWriter.println ("{");
            for (Employee emp: t.keySet()){
                printWriter.println ("    " + TAGS[15]);
                printWriter.println ("    " +  "{");
                printWriter.println ("    " + "    " + TAGS[12] + "   " + emp.getId());
                printWriter.println ("    " + "    " + TAGS[2] + "   " + t.get(emp).getType());
                printWriter.println ("    " + "    " + TAGS[14] + "   " + t.get(emp).getCostToPay());
                if(t.get(emp).getExpenseType() != null){
                    printWriter.println ("    " + "    " + TAGS[1] + "   " + t.get(emp).getExpenseType());
                }
                printWriter.println ("    " + "}");
            }
            printWriter.println ("}");
            System.out.println("Export completed! File: " + file.getAbsolutePath());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}