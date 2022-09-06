package inClass;
import java.util.Scanner;
import java.io.*;

public class VirusSimulation {
	
	static int numIndiv;
	static StringBuilder prevIteration;
	static StringBuilder currentIteration;
	static double root;
	
	// test
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int numRecovered = 0;
		int numInfected = 0;
		int numSuseptable = 0;
		
		//ask user to input perfect square 
		System.out.println("Please input a number of individuals. It should be a perfect square value.");
		numIndiv = scnr.nextInt();
		
		//userInput validation for perfect square
		root = Math.sqrt(numIndiv);
		while (((Math.floor(root)-root)!=0) || numIndiv<0){
			System.out.println("Please input a number of individuals. It should be a perfect square value and a positive value.");
			numIndiv = scnr.nextInt();
		}
		int numRows = (int) Math.sqrt(numIndiv);//used for later calculations
		
		//input validation for choosing infected person
		System.out.println("Please select a person you would like to be infected. It should be be between 0 and the number of individuals specified.");
		int indexOfInfected = scnr.nextInt();
		while (indexOfInfected <= 0 || indexOfInfected >= numIndiv) {
			System.out.println("Please select a person you would like to be infected. It should be be between 0 and the number of individuals specified.");
			indexOfInfected = scnr.nextInt();
		}
		
		//user input validation for numSteps (number of steps)
		int numSteps;
		do {
			System.out.println("Please input the number of times the simulation will run.");
			numSteps = scnr.nextInt();
		} while(numSteps<=0);
		
		// user input validation for alpha (infection rate) 
		float alpha;
		do{
			System.out.println("Enter the rate of infection alpha (alpha <= .25): ");
			alpha = scnr.nextFloat();
		} while (alpha<0 || alpha>.25);
		
		// user input validation for beta (recovery rate)
		float beta;
		do {
			System.out.println("Please input the recovery rate. It should be between 0 and 1.");
			beta = scnr.nextFloat();
		} while(beta<0 || beta>1);
		
		//create string based on user input
		int counter = 0;
		String createdString = "";
		while (numIndiv>counter) {
			if (counter+1==indexOfInfected) {
				createdString = createdString + "I";
				++counter;
			} else {
				createdString = createdString + "S";
				++counter;
			}
		}
		System.out.println("First");
		for(int i = 0; i < numIndiv; i++) {
			System.out.print(createdString.charAt(i));
			if((i+1) % root == 0) {
				System.out.println("");
			}
		}
		System.out.println("");
		
		prevIteration = new StringBuilder(createdString);
		currentIteration = new StringBuilder(prevIteration);
		
		//print the very first iteration only
		try {
			//create a file named virussim if it doesn't exist already, if it exists then append to it
			FileWriter fWriter = new FileWriter("virussim.txt", true);
			PrintWriter pWriter = new PrintWriter(fWriter);
			int infecNum = 0;
			int recovNum = 0;
			int susNum = 0;
			int gcd = 0;
			pWriter.println("This is what the grid will start as.");
			for (int k= 1;currentIteration.length()>=k;++k) {
				if(currentIteration.charAt(k-1)=='I') {
					//count number of infected
					infecNum++;
				}
				if(currentIteration.charAt(k-1)=='R') {
					//count number of recovered
					recovNum++;
				}
				if(currentIteration.charAt(k-1)=='S') {
					//count number of susceptible
					susNum++;
				}
				//print out grid
				System.out.print(currentIteration.charAt(k-1));
				pWriter.print(currentIteration.charAt(k-1));
				if (k%numRows == 0 && k>0) {
					System.out.println("");
					pWriter.println("");
				}
			}
			//find the greatest common denominator for the ratio
			int num1 = infecNum;
			int num2 = numIndiv;
			while (num1 != num2) {
				if (num1 > num2) {
					num1 = num1 - num2;
					gcd = num1;
				} else {
					num2 = num2 - num1;
					gcd = num2;
				}
			}
			pWriter.println("Number of infected individuals: " + infecNum);
			pWriter.println("Number of recovered individuals: " + recovNum);
			pWriter.println("Number of susceptible individuals: " + susNum);
			pWriter.println("Ratio of infected individuals to total number of individuals: " + (infecNum/gcd) + ":" + (numIndiv/gcd));
			pWriter.println("The following grids will show the results of the program running " + numSteps + " times.");
			pWriter.println();
			pWriter.close();
		} catch (IOException ioe) {
			System.out.println("Error has occurred.");
		}
		
		//need this syntax to change individual characters of string
		int currentRowNum = 1;//declare row number
		for (int a = 0; a<numSteps;++a) {
			for (int stringIndex = 1; stringIndex<=numIndiv; stringIndex++) {//iterate through entire string char by char
				//check is user is already recovered
				if (prevIteration.charAt(stringIndex-1)=='R') {
					if (stringIndex%numRows==0 && stringIndex > 0 && stringIndex<numIndiv) {//increment currentRowNum
				        ++currentRowNum;
					}
					continue;
				}
				
				//check if top left corner piece
				if (stringIndex==1) {
					if((testRight(stringIndex) + testBottom(stringIndex)) > 0) {
						if(getNumber0to100() <= ((alpha * 100) * (testRight(stringIndex) + testBottom(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						}
					}
				}
				//check if top right corner piece
				else if (stringIndex==numRows) {
					if((testLeft(stringIndex) + testBottom(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testLeft(stringIndex)+testBottom(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						}
					}
				}
				//check if bottom left corner piece
				else if (stringIndex==(numIndiv-numRows)+1) {
					if((testRight(stringIndex) + testTop(stringIndex)) > 0) {
						if(getNumber0to100() <= ((100 * alpha) * (testRight(stringIndex)+testTop(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						}	
					}
				}
				//check if bottom right corner piece
				else if (stringIndex==numIndiv) {
					if((testLeft(stringIndex) + testTop(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testLeft(stringIndex)+testTop(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						}
					}
				}
				//check if right edge piece
				else if (stringIndex>numRows && stringIndex!=numIndiv && stringIndex%numRows == 0 && stringIndex!=1 && stringIndex!=numRows && stringIndex!=numIndiv-numRows+1) {
					if((testLeft(stringIndex) + testBottom(stringIndex) + testTop(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testLeft(stringIndex)+testBottom(stringIndex)+testTop(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						}
					}
				}
				//check if left edge piece
				else if (currentRowNum>1 && stringIndex==(currentRowNum-1)*(numRows)+1 && stringIndex!=numIndiv-numRows+1 && stringIndex!=1 && stringIndex!=numRows && stringIndex!=numIndiv-numRows+1) {
					if((testRight(stringIndex) + testBottom(stringIndex) + testTop(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testRight(stringIndex)+testBottom(stringIndex)+testTop(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I'); //change corner person's status 
						} 
					}
				}
				//check for top edge pieces
				else if (stringIndex < numRows && stringIndex>0 && stringIndex!=1 && stringIndex!=numRows && stringIndex!=numIndiv-numRows+1) {
					if((testLeft(stringIndex) + testBottom(stringIndex) + testRight(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testBottom(stringIndex)+testLeft(stringIndex)+testRight(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I');//change corner person's status 
						}
					}
				}
				//check if bottom edge piece
				else if (currentRowNum == numRows && stringIndex>((currentRowNum-1)*(numRows)+1) && stringIndex<numIndiv && stringIndex!=1 && stringIndex!=numRows && stringIndex!=numIndiv-numRows+1) {
					if((testLeft(stringIndex) + testRight(stringIndex) + testTop(stringIndex)) > 0) {	
						if(getNumber0to100()<=((100 * alpha) *(testTop(stringIndex)+testLeft(stringIndex)+testRight(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I');//change corner person's status 
						}
					}
				}
				//all inside people
				else {
					if((testLeft(stringIndex) + testBottom(stringIndex) + testTop(stringIndex) + testRight(stringIndex)) > 0) {
						if(getNumber0to100()<=((100 * alpha) * (testLeft(stringIndex)+testRight(stringIndex)+testBottom(stringIndex)+testTop(stringIndex)))) {
							currentIteration.setCharAt(stringIndex-1,'I');//change inside people status
						}
					}
				}
				//increment currentRowNum 
				if (stringIndex%numRows==0 && stringIndex > 0 && stringIndex<numIndiv) {
			        ++currentRowNum;
				}
				
				
				if(prevIteration.charAt(stringIndex-1) == 'I') {
					if(getNumber0to100() <= (beta * 100)) {
						currentIteration.setCharAt(stringIndex-1, 'R');
					}
				}
			}

			for(int i = 0; i < prevIteration.length(); i++) {
				prevIteration.setCharAt(i, currentIteration.charAt(i));
			}
			currentRowNum = 1;
			
			//THIS IS FOR OUTPUT TO CONSOLE ONLY
			for(int i = 0; i < numIndiv; i++) {
				System.out.print(currentIteration.charAt(i));
				if((i+1) % root == 0) {
					System.out.println("");
				}
			}
			System.out.println("");
			
			
			
			try {
				//append to virussim
				FileWriter fWriter2 = new FileWriter("virussim.txt", true);
				PrintWriter pWriter2 = new PrintWriter(fWriter2);
				int infecNum = 0;
				int recovNum = 0;
				int susNum = 0;
				int gcd = numIndiv;
				pWriter2.println("Step " + (a+1) + ":");
				for (int k= 0;k<numIndiv;k++) {
					if(currentIteration.charAt(k)=='I') {
						//count number of infected
						infecNum++;
					}
					if(currentIteration.charAt(k)=='R') {
						//count number of recovered
						recovNum++;
					}
					if(currentIteration.charAt(k)=='S') {
						//count number of susceptible
						susNum++;
					}
					//print out the grid
					pWriter2.print(currentIteration.charAt(k));
					if ((k+1)%root == 0) {
						
						pWriter2.println("");
					}
				}
				//get the greatest common denominator for the ratio
				int num1 = infecNum;
				int num2 = numIndiv;
				while ((num1 != num2) && (num1 != 0)) {
					if (num1 > num2) {
						num1 = num1 - num2;
						gcd = num1;
					} else {
						num2 = num2 - num1;
						gcd = num2;
					}
				}
				pWriter2.println("Number of infected individuals: " + infecNum);
				pWriter2.println("Number of recovered individuals: " + recovNum);
				pWriter2.println("Number of susceptible individuals: " + susNum);
				pWriter2.println("Ratio of infected individuals to total number of individuals: " + (infecNum/gcd) + ":" + (numIndiv/gcd));
				pWriter2.println();
				pWriter2.close();
			} catch (IOException ioe) {
				System.out.println("Error has occurred.");
			}
		
		}
	}
	
	
		
		public static int testTop(int stringIndex) {
			int isInfected = 0;
			if(prevIteration.charAt(stringIndex-1-(int)root)=='I') {
				isInfected = 1;
			}
				return isInfected;
		}
		public static int testLeft(int stringIndex) {
			int isInfected = 0;
			if(prevIteration.charAt(stringIndex-2)=='I') {
				isInfected = 1;
			}
			return isInfected;
		}
		public static int testRight(int stringIndex) {
			int isInfected = 0;
			if(prevIteration.charAt(stringIndex)=='I') {
				isInfected = 1;
			}
			return isInfected;
		}
		public static int testBottom(int stringIndex) {
			int isInfected = 0;
			if(prevIteration.charAt(stringIndex-1+(int)root)=='I') {
				isInfected = 1;
			}
			return isInfected;
		}
		
		
		public static int getNumber0to100() {
			double min = Math.min(1, 100);
	        double max = Math.max(1, 100);
	        return (int) (min + (Math.random() * (max - min)));
	        //return 0;
		}
		
}