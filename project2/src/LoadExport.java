import java.io.*;
import java.util.*;

public class LoadExport {
    private static String[] TAGS = {"EXPENSE_TYPE_LIST", "EXPENSE_TYPE", "TYPE", "CODE", "DESCR", "EMPLOYEE_LIST", "EMPLOYEE", "SURNAME", "FIRSTNAME", "MAX_MONTHLY_VAL",
            "EXPENSE_LIST", "EXPENSE", "EMPLOYEE_CODE", "EXPENSE_CODE", "VAL", "TRN_LIST", "TRN", "COST_PER_UNIT", "UNIT", "PERCENTAGE"};

    private LoadExport() {

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

    public static void ExportData(HashMap<Employee, EmployeeExpense> e, HashMap<Employee, Transaction> t) {
        String exp = System.getProperty("user.home") + "/Desktop" + "/EmployeesExpenses.txt";
        String trn = System.getProperty("user.home") + "/Desktop" + "/EmployeesTransactions.txt";

        try {
            ExportExpenses(e, exp);
            ExportTransactions(t, trn);
        } catch (Exception fnfe) {
            System.out.println("!!!ERROR WHILE SAVING DATA!!!");
        }
    }

    public static void LoadData(String expTypePath, String empListPath, String empExpPath, String empTrnPath) {
        expenseTypes = loadExpenseTypes(expTypePath);
        employees = loadEmployees(empListPath);
        employeesExpenses = loadEmpExpenses(empExpPath);
        employeesTransactions = loadEmpTransactions(empTrnPath);
        System.out.println("Data loading completed successfully!");
    }

    public static ArrayList<ExpenseType> loadExpenseTypes(String path) {

        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<ExpenseType> et = new ArrayList<>();
        String checkpoint = null, currentLine, type = null, code = null, descr = null;
        boolean hasType = false, hasCode = false, hasDescr = false;
        double maxMonValue = 0.0;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
        } catch (Exception ioe) {
            System.out.println("!!!PROBLEM ON EXPENSE_TYPES READING!!!");
        }

        try {
            while ((currentLine = br.readLine()) != null) {
                skipEmptyLines(currentLine, br);
                if (currentLine.trim().equalsIgnoreCase("EXPENSE_TYPE_LIST")) {
                    currentLine = br.readLine();

                    skipEmptyLines(currentLine, br);
                    if (currentLine.trim().equals("{")) {
                        while (!currentLine.trim().equals("}") && currentLine != null) {
                            currentLine = br.readLine();
                            skipEmptyLines(currentLine, br);
                            if (currentLine.trim().equalsIgnoreCase("EXPENSE_TYPE")) {
                                currentLine = br.readLine();
                                skipEmptyLines(currentLine, br);
                                if (currentLine.equals("{")) {
                                    br.mark(10000);

                                    while (!currentLine.equals("}") && currentLine != null) {
                                        checkpoint = currentLine = br.readLine();
                                        if (currentLine.matches("(?i)\\s*code\\s*(\\S+\\s*)*")) {
                                            hasCode = true;
                                            code = currentLine.trim().substring("CODE".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*descr\\s*(\\S+\\s*)*")) {
                                            hasDescr = true;
                                            descr = currentLine.trim().substring("EXPENSE_TYPE_DESCR".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*type\\s*\\S+\\s*")) {
                                            hasType = true;
                                            type = currentLine.trim().substring(5).trim();
                                        }
                                    }
                                }
                                if (hasCode && hasDescr && hasType) {
                                    br.reset();
                                    currentLine = br.readLine();
                                    if (type.trim().equalsIgnoreCase("1")) {
                                        double costPerUnit = 0;
                                        String unit = null;
                                        while (!currentLine.trim().equals("}")) {
                                            if (currentLine.matches("(?i)\\s*cost_per_unit\\s*(\\S+\\s*)*")) {
                                                costPerUnit = Double.parseDouble(currentLine.trim().substring("cost_per_unit".length()).trim());
                                            } else if (currentLine.matches("(?i)\\s*unit\\s*(\\S+\\s*)*")) {
                                                unit = currentLine.trim().substring("UNIT".length());
                                            }
                                            currentLine = br.readLine();
                                        }
                                        et.add(new ExpenseType1(code, descr, maxMonValue, costPerUnit, unit));
                                    } else if (type.trim().equalsIgnoreCase("2")) {
                                        double percentage = 0.0;
                                        if (currentLine.matches("(?i)\\s*percentage\\s*(\\S+\\s*)*")) {
                                            percentage = Double.parseDouble(currentLine.trim().substring("PERCENTAGE".length()).trim());
                                        }
                                        et.add(new ExpenseType2(code, descr, maxMonValue, percentage));
                                    }
                                } else {
                                    System.out.println("File in line: ' " + checkpoint + " ' was skipped during loading due to insufficient data...");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("!!!ERROR DURING EXPENSE_TYPES LOADING!!!");
            et = null;
        }
        return et;
    }

    private static ArrayList<Employee> loadEmployees(String empListPath) {
        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<Employee> e = new ArrayList<>();
        String currentLine = null, checkpoint = null, code = null, firstname = null, surname = null, max_mon_value = null;
        boolean hasCode = false, hasName = false, hasSurname = false, hasMaxMonValue = false;

        try {
            fr = new FileReader(empListPath);
            br = new BufferedReader(fr);
        } catch (Exception ioe) {
            System.out.println("!!!PROBLEM ON EMPLOYEES READING!!!");
        }

        try {
            while ((currentLine = br.readLine()) != null) {
                skipEmptyLines(currentLine, br);
                if (currentLine.trim().equalsIgnoreCase("EMPLOYEE_LIST")) {
                    currentLine = br.readLine();

                    skipEmptyLines(currentLine, br);

                    if (currentLine.trim().equals("{")) {
                        while (!currentLine.trim().equals("}") && currentLine != null) {
                            currentLine = br.readLine();

                            skipEmptyLines(currentLine, br);

                            if (currentLine.trim().equalsIgnoreCase("EMPLOYEE")) {
                                currentLine = br.readLine();

                                skipEmptyLines(currentLine, br);

                                if (currentLine.equals("{")) {
                                    while (!currentLine.equals("}") && currentLine != null) {
                                        checkpoint = currentLine = br.readLine();
                                        if (currentLine.matches("(?i)\\s*code\\s*(\\S+\\s*)*")) {
                                            hasCode = true;
                                            code = currentLine.trim().substring("CODE".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*firstname\\s*(\\S+\\s*)*")) {
                                            hasName = true;
                                            firstname = currentLine.trim().substring("FIRSTNAME".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*lastname\\s*(\\S+\\s*)*")) {
                                            hasSurname = true;
                                            surname = currentLine.trim().substring("LASTNAME".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*max_monthly_val\\s*(\\S+\\s*)*")) {
                                            hasMaxMonValue = true;
                                            max_mon_value = currentLine.trim().substring("LASTNAME".length()).trim();
                                        }
                                    }
                                }

                                if (hasCode && hasName && hasSurname) {
                                    Double mmv;
                                    if (hasMaxMonValue) {
                                        mmv = Double.parseDouble(max_mon_value);
                                    } else {
                                        mmv = 200.0;
                                    }
                                    e.add(new Employee(code, surname, firstname, mmv));
                                } else {
                                    System.out.println("File in line: ' " + checkpoint + " ' was skipped during loading due to insufficient data...");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception exc) {
            System.out.println("!!!ERROR DURING EMPLOYEES LOADING!!!");
            e = null;
        }
        return e;
    }

    private static HashMap<Employee, EmployeeExpense> loadEmpExpenses(String empExpPath) {
        BufferedReader br = null;
        FileReader fr = null;
        HashMap<Employee, EmployeeExpense> ee = new HashMap<>();
        String checkpoint = null, currentLine = null, emp_code = null, exp_type = null, exp_code = null, val = null;
        boolean hasEmpCode = false, hasExpType = false, hasExpCode = false, hasVal = false;

        try {
            fr = new FileReader(empExpPath);
            br = new BufferedReader(fr);
        } catch (Exception ioe) {
            System.out.println("!!!PROBLEM ON EMPLOYEE_EXPENSES READING!!!");
        }

        try {
            while ((currentLine = br.readLine()) != null) {
                skipEmptyLines(currentLine, br);
                if (currentLine.trim().equalsIgnoreCase("EXPENSE_LIST")) {
                    currentLine = br.readLine();
                    skipEmptyLines(currentLine, br);
                    if (currentLine.trim().equals("{")) {
                        while (!currentLine.trim().equals("}") && currentLine != null) {
                            currentLine = br.readLine();
                            skipEmptyLines(currentLine, br);
                            if (currentLine.trim().equalsIgnoreCase("EXPENSE")) {
                                currentLine = br.readLine();
                                skipEmptyLines(currentLine, br);
                                if (currentLine.equals("{")) {
                                    while (!currentLine.equals("}") && currentLine != null) {
                                        checkpoint = currentLine = br.readLine();
                                        if (currentLine.matches("(?i)\\s*employee_code\\s*(\\S+\\s*)*")) {
                                            hasEmpCode = true;
                                            emp_code = currentLine.trim().substring("EMPLOYEE_CODE".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*expense_type\\s*(\\S+\\s*)*")) {
                                            hasExpType = true;
                                            exp_type = currentLine.trim().substring("EXPENSE_TYPE".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*expense_code\\s*(\\S+\\s*)*")) {
                                            hasExpCode = true;
                                            exp_code = currentLine.trim().substring("EXPENSE_CODE".length()).trim();
                                        } else if (currentLine.matches("(?i)\\s*val\\s*(\\S+\\s*)*")) {
                                            hasVal = true;
                                            val = currentLine.trim().substring("VAL".length()).trim();
                                        }
                                    }
                                }
                                if (hasEmpCode && hasExpType && hasExpCode && hasVal) {
                                    Employee emp = searchForEmployee(emp_code);
                                    ExpenseType et = searchForExpenseType(exp_type, exp_code);
                                    Double value = Double.parseDouble(val);
                                    if (emp != null && et != null) {
                                        EmployeeExpense expense = new EmployeeExpense(emp, et, value, et.getDescription());
                                        ee.put(emp, expense);
                                    } else {
                                        if (emp != null) {
                                            System.out.println("File in line: ' " + checkpoint + " ' was skipped due to invalid Employee Code");
                                        }
                                        if (et != null) {
                                            System.out.println("File in line: ' " + checkpoint + " ' was skipped due to invalid Expense Type");
                                        }
                                    }

                                } else {
                                    System.out.println("File in line: ' " + checkpoint + " ' was skipped during loading due to insufficient data...");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("!!!ERROR DURING EMPLOYEE_EXPENSES LOADING!!!");
            ee = null;
        }

        return ee;
    }

    private static ExpenseType searchForExpenseType(String exp_type, String exp_code) {
        for (ExpenseType et : expenseTypes) {
            if (et.getType().equalsIgnoreCase(exp_type) && et.getId().equals(exp_code)) {
                return et;
            }
        }
        return null;
    }

    private static Employee searchForEmployee(String emp_code) {
        for (Employee e : employees) {
            if (e.getId().equals(emp_code)) {
                return e;
            }
        }
        return null;
    }

    private static HashMap<Employee, Transaction> loadEmpTransactions(String empTrnPath) {
        //TODO implement loadEmpTransactions
        return null;
    }

    private static void ExportExpenses(HashMap<Employee, EmployeeExpense> e, String exp) {
        File file = new File(exp);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(TAGS[10]);
            printWriter.println("{");
            for (Employee emp : e.keySet()) {
                printWriter.println("    " + TAGS[11]);
                printWriter.println("    " + "{");
                printWriter.println("    " + "    " + TAGS[12] + "   " + emp.getId());
                printWriter.println("    " + "    " + TAGS[13] + "   " + e.get(emp).getExpenseType().getType());
                printWriter.println("    " + "    " + TAGS[14] + "   " + e.get(emp).getValue());
                printWriter.println("    " + "}");
            }
            printWriter.println("}");
            System.out.println("Export completed! File: " + file.getAbsolutePath());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private static void ExportTransactions(HashMap<Employee, Transaction> t, String trn) {
        File file = new File(trn);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(TAGS[15]);
            printWriter.println("{");
            for (Employee emp : t.keySet()) {
                printWriter.println("    " + TAGS[15]);
                printWriter.println("    " + "{");
                printWriter.println("    " + "    " + TAGS[12] + "   " + emp.getId());
                printWriter.println("    " + "    " + TAGS[2] + "   " + t.get(emp).getType());
                printWriter.println("    " + "    " + TAGS[14] + "   " + t.get(emp).getCostToPay());
                if (t.get(emp).getExpenseType() != null) {
                    printWriter.println("    " + "    " + TAGS[1] + "   " + t.get(emp).getExpenseType());
                }
                printWriter.println("    " + "}");
            }
            printWriter.println("}");
            System.out.println("Export completed! File: " + file.getAbsolutePath());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private static void skipEmptyLines(String currentLine, BufferedReader br) {

        while (currentLine.trim().isEmpty()) {
            try {
                currentLine = br.readLine(); //skip blank lines
            } catch (IOException e) {
                continue;
            }
        }
    }
}