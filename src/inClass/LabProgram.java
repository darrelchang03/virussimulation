package inClass;

import java.util.Scanner; 

	public class LabProgram {
	 
		public static void printSelectedNumbers(int numCount) {
		      Scanner scnr = new Scanner(System.in);
		      int i;
		      int number;
		   
		      for (i = 0; i < numCount; ++i) {
		         number = scnr.nextInt();
		         if ((number % 5) != 0) {
		            System.out.println(number);
		         }
		      }
		    }
		    
		   public static void main(String[] args) {
		      int numCount = 7;

		      printSelectedNumbers(numCount);
		   }
		}