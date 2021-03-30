import java.util.Scanner;
import java.lang.Math;

/*************************************************************
 *  Compilation:  		javac Application1.java
 *  Execution:    		java Application1
 *
 *	Student Name:		Ioannis Papachristou
 *  Student ID Number:	3180150
 *
 *************************************************************/
 
public class Application1 {
	
	// ---------------------- Q1 ---------------------------- 
	public static boolean isPrime (int n) {
		// Fill your code here
		int check = 0;
		
		if(n==0 || n==1) {
			check = 1;
		}
		else {
			for(int i = 2; i < n/2; i++) {
				if(n % i == 0) {
					check = 1;
					break;
				}
			}
		}
		if(check == 0){
			return true;
		}
		else {
			return false;
		}
		
		
	}//isPrime
		
	// ---------------------- Q2 ---------------------------- 
	public static int factorial(int n) {
		// Fill your code here
		int fact = 1;
		
		for(int i = 1; i<=n; i++) {
			fact *= i;
		}
		return fact;
	
	
	}//factorial
	
	// ---------------------- Q3 ---------------------------- 	
	public static int combinations(int n, int k) {
		// Fill your code here
		int comb = 0;
		
		int a = factorial(n);
		int b = factorial(k);
		int diff = n-k;
		int c = factorial(diff);
		
		comb = a/(b*c);
		
		return comb;
		
	}// combinations
	
	// ---------------------- Q4 ---------------------------- 
	public static int digitsOfInteger (int n) {
		// Fill your code here
		
		int count = 0;
		
		while(n != 0) {
			count += 1;			
			n = n / 10;
		}
		return count;
		
	}//digitsOfInteger

	// ---------------------- Q5 ---------------------------- 
	public static void quadraticEquation(double a, double b, double c) {
		// Fill your code here
		double D = b*b - 4*a*c;
		if (D < 0) {
			System.out.println("There aren't real solutions");
		}
		else {
			double s1 = (-b - Math.sqrt(D)) / (2*a);
			double s2 = (-b + Math.sqrt(D)) / (2*a);
			
			System.out.printf("\nThe first solution is: %.2f %n", s1);
			System.out.printf("\nThe second solution is %.2f %n", s2);
		}
				
		
	}//quadraticEquation

	// ---------------------- Q6 ---------------------------- 
	public static double findArea(double a, double b, double c) {
		// Fill your code here
		double t = (a+b+c)/2.0;
		
		if(t<=0){
			return -1;
		}
		else {
			double E = Math.sqrt(t*(t-a)*(t-b)*(t-c));
			
			return E;
		}	
		
	}//findArea

	// ---------------------- Q7 ---------------------------- 
	public static int reverseDigits (int n) {
		// Fill your code here
		int placement = 0;
		
		while(n != 0) {
			int d = n % 10;
			placement = d + placement * 10; // in placement is saved the position that the new digit must be placed
											// multiplying placement by 10 in each loop we jump one tenth(tenth*10 = hundredth etc)
			n = n / 10;
		}
		int reversedNumber = placement; // after the last loop placement contains the totally reversed number 
		return  reversedNumber;
		
	}//reverseDigits

	// ---------------------- Q8 ---------------------------- 
	public static int minDigit(int n) {
		// Fill your code here
		
		int min = 9; // each digit is between 0 and 9 so min is getting initialized in the max value
		
		while(n != 0) {
			int d = n % 10;
			
			min = Math.min(min, d);
			
			n = n / 10;
		}
		return min;		
		
	}//minDigit
	
		
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		
		// Fill your code here
		
		Scanner sc = new Scanner(System.in);
		
		
		// ---------------- Q1 ----------------------------
		System.out.println("\n\nQ1 - Prime Number (n)");
		// Fill your code here
		System.out.println("Enter n > 1: ");
		
		int n = sc.nextInt();
		boolean response = isPrime(n);
		
		if(response) {
			System.out.println(n + " is a Prime Number");
		}
		else {
			System.out.println(n + " isn't a Prime Number");
		}
		
		
		// ---------------- Q2 ----------------------------
		System.out.println("\n\nQ2 - Factorial (n)");
		// Fill your code here
		System.out.println("Enter n >= 0: ");
		
		n = sc.nextInt();
		
		int fact = factorial(n);
		System.out.println("Factorial(" + n + ") = " + fact);		
		
		// ---------------- Q3 ----------------------------
		System.out.println("\n\nQ3 - Combinations (n,k)");
		// Fill your code here
		
		System.out.println("Enter n: ");		
		n = sc.nextInt();
		
		System.out.println("Enter k: ");		
		int k = sc.nextInt();
		
		int comb = combinations(n, k);
		System.out.println("Combinations(" + n + "," + k + ") = " + comb);		

		// ---------------- Q4 ----------------------------	
		System.out.println("\n\nQ4 - Number of digits");
		// Fill your code here
		
		System.out.println("Enter a positive integer: ");
		n = sc.nextInt();
		
		int number = digitsOfInteger(n);
		
		System.out.println("Number of digits = " + number);
		
		
		// ---------------- Q5 ----------------------------
		System.out.println("\n\nQ5 - Quadratic equation...");
		// Fill your code here
		
		System.out.println("Enter a <> 0: ");		
		int a = sc.nextInt();
		
		System.out.println("Enter b: ");
		int b = sc.nextInt();
		
		System.out.println("Enter c: ");
		int c = sc.nextInt();
		
		quadraticEquation(a, b, c);		
		
		// ---------------- Q6 ----------------------------
		System.out.println("\n\nQ6 - Area of a triangle");
		// Fill your code here
		
		System.out.println("Enter the length of the three sides.");
		System.out.println("Enter a <> 0: ");		
		a = sc.nextInt();
		
		System.out.println("Enter b: ");
		b = sc.nextInt();
		
		System.out.println("Enter c: ");
		c = sc.nextInt();
		
		double area = findArea(a, b, c);
		
		if (area == -1) {
			System.out.println("Area: -1 ; Triangle doesn't exist...");
		}
		else {
			System.out.printf("The area of the triangle is: %.2f %n", area);
		}		
		
		
		// ---------------- Q7 ----------------------------
		System.out.println("\n\nQ7 - Reverse digits");
		// Fill your code here
		
		System.out.println("Enter a positive integer: ");
		n = sc.nextInt();	
		
		int reversed = reverseDigits(n);
		
		System.out.println("The reversed number is: " + reversed);
		
		
		// ---------------- Q8 ----------------------------	
		System.out.println("\n\nQ8 - Min Digit");
		// Fill your code here
		System.out.println("Enter a positive integer: ");
		n = sc.nextInt();
		
		int min = minDigit(n);
		
		System.out.println("Min Digit = " + min);
		
    } //main

} //Application1