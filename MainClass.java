package Lab1Finals;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class MainClass {
	 public static void main(String[] args) {
	        ShoppingList shoppingList = new ShoppingList();
	        shoppingList.loadFromFile(); // Load items from a file (if available)
	        Scanner scanner = new Scanner(System.in);
	        int choice = 0;

	        System.out.println("My Shopping List.");
	        System.out.println("*************************************");

	        try {
	        	while (choice != 5) {
		            System.out.println("Choose one of the following:");
		            System.out.println("1. Display Items.");
		            System.out.println("2. Add Items.");
		            System.out.println("3. Buy.");
		            System.out.println("4. Remove Item.");
		            System.out.println("5. Save and Exit.");

		            choice = scanner.nextInt();
		            scanner.nextLine();

		            if (choice == 1) {
		                shoppingList.displayItems();
		            } else if (choice == 2) {
		                System.out.print("Enter the number of items to add: ");
		                int itemCount = scanner.nextInt();
		                scanner.nextLine();

		                for (int i = 0; i < itemCount; i++) {
		                    System.out.println("Item " + (i + 1));
		                    System.out.print("Name: ");
		                    String itemName = scanner.nextLine();
		                    System.out.print("Description: ");
		                    String itemDescription = scanner.nextLine();
		                    System.out.print("Price: ");
		                    double itemPrice = scanner.nextDouble();
		                    scanner.nextLine();
		                    shoppingList.addItem(itemName, itemDescription, itemPrice);
		                    System.out.println("Item added to the shopping list.");
		                }
		            } else if (choice == 3) {
		            	shoppingList.displayItems();
		                System.out.print("Enter the name of the item to mark as purchased: ");
		                String itemName = scanner.nextLine();
		                shoppingList.markAsPurchased(itemName);
		            } else if (choice == 4) {
		            	shoppingList.displayItems();
		                System.out.print("Enter the name of the item to remove: ");
		                String itemName = scanner.nextLine();
		                shoppingList.deleteItem(itemName);
		            } else if (choice == 5) {
		                shoppingList.saveToFile(); // Save items to a file before exiting
		                System.out.println("Thank you for using the program!");
		            } else {
		                System.out.println("Invalid choice. Please choose a valid option.");
		            }
		        }
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("Out of bounds! Try Again."); 
	        	main(args);
	        }
	  }
}

