import java.util.*;

public class AssignmentISBN {
	
	//Global variables that can be constantly updated and saved through the whole program
	static int returnedBooks = 0;
	static int checkedBooks = 0;
	static int totalBooks = 0;
	
	
	public static void main(String[] args) {
		System.out.println("************************************************\r\n"
				+ "* Welcome to the Daily Book Inventory Program! *\r\n"
				+ "************************************************\r\n");

		isbn();
	}
	
	//This method gets the isbn and loops till it is valid
	public static void isbn() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the first 12 digits of an ISBN-13 as a String: ");
		String isbnNumber = input.nextLine();
		
		//This checks if it is a number and if it is exactly 12 digits long
		if (!isbnNumber.matches("[0-9]+") || isbnNumber.length() != 12 ) {
			System.out.println("Invalid ISBN-13 number! Try again.");
			isbn();
		} else {
			solveISBN(isbnNumber);
		}
	}
	
	//This method takes the approved 12 digit isbn number that was entered by the user and solves the last digit of it
	public static void solveISBN(String isbnNumber) {
		long isbn = Long.parseLong(isbnNumber);
		long reISBN = isbn;
		int tick = 0;
		int sumOne = 0;
		int sumTwo = 0;
		int total = 0;
		
		//This loops to multiply the digit by either 1 (so just add itself to sum) or 3)
		while (isbn > 0) {
			tick++;
			int digit = (int)(isbn % 10);
			if (tick % 2 == 0) {
				sumOne += digit;
			} else if (tick % 2 == 1){
				sumTwo += (digit * 3);
			}
			isbn = isbn/10;
		}
		
		//This is the next part in the isbn algorithm, in which you take the sum of the previous equation and modulo 10 it
		total = ((sumOne + sumTwo) % 10);
		
		//Here it checks if it equals 0 or not, if it does it will just add 0 to the end, if not it will also add the total to the end of it
		if (total != 0) {
			total = (10 - total);
			reISBN = (reISBN * 10 + total);
		} else {
			reISBN = (reISBN * 10 + 0);
		}
		
		System.out.println("The ISBN-13 number is " + reISBN);
		inventory();
	}
	
	//This is the "Hub" method for the inventory, it is basically where any thing having to do with the inventory is called depending on the circumstance 
	public static void inventory() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter 'R' for return or 'C' for check out: ");
		String option = input.nextLine();
		
		if (option.matches("[R]")) {
			returnedBooks++;
			totalBooks++;
		} else if (option.matches("[C]")) {
			checkedBooks++;
			totalBooks++;
		} else {
			inventory();
		}
		
		printStatus();
		redirect();
	}
	
	//This method is used to easily print the inventory at any time from any where
	public static void printStatus() {
		System.out.println("************************************************\r\n"
				+ "*             Daily Book Inventory             *\r\n"
				+ "************************************************\r\n"
				+ "Number of books returned:      " + returnedBooks + "\r\n"
				+ "Number of books checked out:   " + checkedBooks + "\r\n"
				+ "The total of books processed:  " + totalBooks  + "\r\n"
				+ "************************************************");
	}
	
	//This method asks if they want to continue (calls the isbn function to get another isbn number) or quit (prints the status)
	public static void redirect() {
		System.out.print("Enter 'Y' to continue, 'N' to quit: ");
		Scanner input = new Scanner(System.in);
		String option = input.nextLine();
		
		if (option.matches("[Y]")) {
			isbn();
		} else if (option.matches("[N]")) {
			printStatus();
		} else {
			redirect();
		}
	}
}
	

