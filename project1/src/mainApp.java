import javax.management.InvalidAttributeValueException;
import java.util.*;

public class mainApp {
    static ArrayList<ExpenseType> expenseTypes;
    static ArrayList<Employee> employees;
    static HashMap<Employee, EmployeeExpense> employeesExpenses;
    static HashMap<Employee, Transaction> employeesTransactions;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        listsInit();
        boolean loop = true;
        while(loop){
            printHomeMenu();

            try{
                int response = sc.nextInt();

                switch (response) {
                    case 1 -> function1();
                    case 2 -> function2();
                    case 3 -> function3();
                    case 4 -> function4();
                    case 5 -> function5();
                    case 6 -> function6();
                    case 7 -> function7();
                    case 8 -> function8();
                    case 9 -> function9();
                    case 0 -> loop = false;
                    default -> throw new InvalidAttributeValueException();
                }
            }
            catch(Exception e){
                System.out.println("Invalid Input! Please insert a number 0-9");
            }
        }
        System.out.println("\nClosing program...\n");
    }

    public static void listsInit(){
        expenseTypes = new ArrayList<>();

        ExpenseType gasoline = new ExpenseType1("gasoline", 100.0, 1.57, "lt");
        ExpenseType food = new ExpenseType1("food", 600.0, 20.0, "day");
        ExpenseType accommodation = new ExpenseType1("accommodation", 1500.0, 50.0, "day");

        expenseTypes.add(gasoline);
        expenseTypes.add(food);
        expenseTypes.add(accommodation);

        ExpenseType taxi = new ExpenseType2("taxi", 50.0, 0.5);
        ExpenseType restaurant = new ExpenseType2("company's restaurant", 50.0, 0.2);
        ExpenseType pc = new ExpenseType2("pc buying", 100.0, 0.3);

        expenseTypes.add(taxi);
        expenseTypes.add(restaurant);
        expenseTypes.add(pc);

        employees = new ArrayList<>();

        Employee mavridis = new Employee("Mavridis", "Ioannis", 2000.0);
        Employee papachristou = new Employee("Papachristou", "Ioannis", 2000.0);
        Employee kalergis = new Employee("Kalergis", "Christos", 3500.0);
        Employee togatzi = new Employee("Togatzi", "Maria", 3000.0);

        employees.add(mavridis);
        employees.add(papachristou);
        employees.add(kalergis);
        employees.add(togatzi);

        employeesExpenses = new HashMap<>();
        int bigIncr = 10;
        int smallIncr = 0;
        for (Employee e: employees){
            EmployeeExpense ee1 = new EmployeeExpense(e, food, 20+ bigIncr + ++smallIncr, "Food expenses for a daily visit to our offices in Kalamata");
            EmployeeExpense ee2 = new EmployeeExpense(e, gasoline, 60+ bigIncr+5, "Gasoline for travelling by car");
            EmployeeExpense ee3 = new EmployeeExpense(e, restaurant, 7+ ++smallIncr, "Sandwich and coffee before travel");
            EmployeeExpense ee4 = new EmployeeExpense(e, taxi, 13 + ++smallIncr, "Taxi to airport");

            employeesExpenses.put(e, ee1);
            employeesExpenses.put(e, ee2);
            employeesExpenses.put(e, ee3);
            employeesExpenses.put(e, ee4);
        }

        employeesTransactions = new HashMap<>();
        for (Employee e: employees){
            Transaction t = new Transaction(e, 50.0);
            employeesTransactions.put(e, t);
        }
    }

    public static void printHomeMenu(){
        System.out.println("\n--------Home Menu---------\n");
        System.out.println("1. Add new expense type");
        System.out.println("2. Add new employee");
        System.out.println("3. Add new employee's expense");
        System.out.println("4. Add new payment in advance");
        System.out.println("5. Show employees' expenses");
        System.out.println("6. Pay employee's expenses");
        System.out.println("7. Show employees' transactions");
        System.out.println("8. Pay everyone's expenses");
        System.out.println("9. Show final monthly cost for each employee");
        System.out.println("0. Exit\n");
        System.out.print("Choose operation: (0-9)\n");
    }

    public static void function1(){     //add new expense type
        System.out.println("\nNew Expense Type\n");
        ExpenseType toAdd;

        String description = null;
        do{
            try{
                System.out.println("Insert description: \n");
                description = sc.next();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while(description==null);

        double maxValue = -1;

        do{
            try{
                System.out.println("Insert Maximum Monthly Value: (0 for no limit)\n");
                maxValue = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while(maxValue==-1);

        System.out.println("Insert Type: (1 or 2)\n");
        int response = 0;
        do{
            try{
                response = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid Input! Please insert 1 or 2");
            }
        }while(response != 1 && response != 2);

        switch (response) {
            case 1 -> {
                String unit = null;
                do{
                    try{
                        System.out.println("Insert Unit");
                        unit = sc.next();
                    }
                    catch(Exception e){
                        System.out.println("Invalid Input");
                    }
                }
                while(unit==null);

                double costPerUnit = -1;

                do{
                    try{
                        System.out.println("Insert cost per " + unit);
                        costPerUnit = sc.nextDouble();
                    }
                    catch(Exception e){
                        System.out.println("Invalid Input");
                    }
                }
                while(costPerUnit==-1);

                toAdd = new ExpenseType1(description, maxValue, costPerUnit, unit);
                expenseTypes.add(toAdd);
            }
            case 2 -> {

                double percentage = -1;

                do{
                    try{
                        System.out.println("Insert payment percentage");
                        percentage = sc.nextDouble();
                    }
                    catch(Exception e){
                        System.out.println("Invalid Input");
                    }
                }
                while(percentage==-1);

                toAdd = new ExpenseType2(description, maxValue, percentage);
                expenseTypes.add(toAdd);
            }
        }
    }

    public static void function2(){
        System.out.println("\nNew Employee\n");
        Employee toAdd;

        String lastname = null;
        do{
            try{
                System.out.println("Insert Last Name: \n");
                lastname = sc.next();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while(lastname==null);

        String firstname = null;
        do{
            try{
                System.out.println("Insert First Name: \n");
                firstname = sc.next();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while(firstname==null);

        double max_payment = -1;
        do{
            try{
                System.out.println("Insert Max Monthly Payment: \n");
                max_payment = sc.nextDouble();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }
        while(max_payment==-1);

        toAdd = new Employee(lastname, firstname, max_payment);
        employees.add(toAdd);
    }

    public static void function3(){
        System.out.println("New Employee's Expense");
        EmployeeExpense toAdd;

        listToString(employees);

        int response = -1;
        do{
            try{
                System.out.println("Choose Employee: (Numbers only)\n");
                response = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }while(response == -1);

        Employee emp = employees.get(response);

        listToString(expenseTypes);
        response = -1;
        do{
            try{
                System.out.println("Choose Expense Type: (Numbers only, type -2 to add a new expense type first)\n");
                response = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }while(response == -1);

        if(response == -2){
            function1();
        }

        ExpenseType et = expenseTypes.get(response);

        double value = -1;

        do{
            try{
                System.out.println("Insert expense value: ");
                value = sc.nextDouble();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }while(value == -1);

        String reasoning = null;

        do{
            try{
                System.out.println("Insert expense reasoning: ");
                reasoning = sc.next();
            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }
        }while(reasoning == null);

        toAdd = new EmployeeExpense(emp, et, value, reasoning);
        employeesExpenses.put(emp, toAdd);
    }

    public static void function4(){
        System.out.println("New payment in advance");

    }

    public static void function5(){}

    public static void function6(){}

    public static void function7(){}

    public static void function8(){}

    public static void function9(){}

    public static void listToString(ArrayList a){
        int i = 0;
        for (Object s : a)
        {
            String listString = i++ + ". " + s.toString();
            System.out.println(listString);
        }
    }

    public static void mapToString(HashMap h){
        int i = 0;
        for (Object e : h.keySet()){
            String mapString = i++ + e.toString() + ". " + ":\t" + h.get(e).toString();
            System.out.println(mapString);
        }
    }
}
