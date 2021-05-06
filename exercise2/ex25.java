public class ex25 { 

	public static void main(String args[]) { 

		double[] grades = {7, 9, 10, 8.5, 8, 9.5, 2, 4, 7, 8};
		
		double[] grades_backup = new double[grades.length];
		
		System.out.println( "mean value: " + meanValue (grades) ); 
		System.out.println( "max value: " + maxValue (grades) ); 
		System.out.println( "min value: " + minValue (grades) ); 
		myCopy (grades, grades_backup); 
		printArray (grades); 
		printArray (grades_backup); 
	} 

	private static double meanValue(double[] array) { 
		double res = 0;
		for (int i=0; i<array.length; i++){res += array[i];}
		res = res/array.length;
		return res;
	} 

	private static double maxValue(double[] array) { 
		double res = 0;
		for (int i=0; i<array.length; i++){if(array[i]>res){res = array[i];}}
		return res;
	} 

	private static double minValue(double[] array) { 
		double res = 10;
		for (int i=0; i<array.length; i++){if(array[i]<res){res = array[i];}}
		return res;
	} 

	private static void myCopy(double[] grades, double[] grades_backup) { 
		for (int i = 0; i<grades.length; i++){
			grades_backup[i] = grades[i];}
	} 

	private static void printArray(double[] array) { 
		for (int i=0; i<array.length; i++){System.out.print(array[i] + "|");}
		System.out.println();
	} 
}
