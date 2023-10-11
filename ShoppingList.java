package Lab1Finals;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class ShoppingList {
	 ShoppingListItem head;
	    DecimalFormat df = new DecimalFormat("0.00");
	    Scanner scanner = new Scanner(System.in);

	    void addItem(String itemName, String itemDescription, double itemPrice) {
	        ShoppingListItem newItem = new ShoppingListItem(itemName, itemDescription, itemPrice);
	        if (head == null) {
	            head = newItem;
	        } else {
	            ShoppingListItem current = head;
	            while (current.next != null) {
	                current = current.next;
	            }
	            current.next = newItem;
	        }
	    }

	    void markAsPurchased(String itemName) {
	        ShoppingListItem current = head;
	        double userMoney;
	        
	        while (current != null) {
	            if (current.itemName.equals(itemName)) {
	                if (!current.purchased) {
	                    System.out.println("Item Cost: P" + current.itemPrice);
	                    double moneySpent = 0;
	                    while (true) {
	                        System.out.print("Enter the amount of your money: P");
	                        userMoney = scanner.nextDouble();
	                        scanner.nextLine();
	                        if (userMoney < current.itemPrice) {
	                            System.out.println("Insufficient money. Do you want to add more money? (Y/N)");
	                            String addMoreMoney = scanner.nextLine().toLowerCase();
	                            if (addMoreMoney.equals("n")) {
	                                System.out.println("Item not purchased. Returning to the menu.");
	                                return;
	                            }
	                        } else {
	                            moneySpent = current.itemPrice;
	                            break;
	                        }
	                    }

	                    if (userMoney > current.itemPrice) {
	                        double remainingMoney = userMoney - current.itemPrice;
	                        System.out.println("Remaining money: P" + remainingMoney);
	                        System.out.println("Thank you for buying!");
	                    }

	                    current.purchased = true;
	                    System.out.println("Item marked as purchased.");
	                    return;
	                } else {
	                    System.out.println("Item is already purchased.");
	                    return;
	                }
	            }
	            current = current.next;
	        }
	        System.out.println("Item not found in the shopping list.");
	    }


	    void deleteItem(String itemName) {
	        if (head == null) {
	            System.out.println("Shopping List is empty. Cannot delete.");
	            return;
	        }

	        if (head.itemName.equals(itemName)) {
	            head = head.next;
	            System.out.println("Item removed from the shopping list.");
	            return;
	        }

	        ShoppingListItem current = head;
	        while (current.next != null) {
	            if (current.next.itemName.equals(itemName)) {
	                current.next = current.next.next;
	                System.out.println("Item removed from the shopping list.");
	                return;
	            }
	            current = current.next;
	        }

	        System.out.println("Item not found in the shopping list.");
	    }

	    void displayItems() {
	        if (head == null) {
	            System.out.println("Shopping List is empty.");
	            return;
	        }
	        ShoppingListItem current = head;
	        System.out.println("Shopping List:\n");

	        while (current != null) {
	            String purchasedStatus = current.purchased ? " (Purchased)" : "";
	            System.out.println("Item Name: " + current.itemName + purchasedStatus);
	            System.out.println("Item Description: " + current.itemDescription);
	            String formattedPrice = df.format(current.itemPrice);
	            System.out.println("Item Price: P" + formattedPrice);
	            System.out.println("***********************************************\n");
	            current = current.next;
	        }
	    }
	    void saveToFile() {
	        try {
	            FileWriter writer = new FileWriter("D:/Desktop/myFiles/College/2nd year college/1st Term/Data Structures & Algorithms/Programming/HomeworkShoppingList/ShoppingList.txt");
	            ShoppingListItem current = head;
	            while (current != null) {
	                writer.write(current.itemName + "," + current.itemDescription + "," + df.format(current.itemPrice) + "," + current.purchased + "\n");
	                current = current.next;
	            }
	            writer.close();
	            System.out.println("Shopping list saved to file.");
	        } catch (IOException e) {
	            System.out.println("Error while saving the shopping list to a file.");
	        }
	    }

	    void loadFromFile() {
	        head = null; // Clear the current list
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("D:/Desktop/myFiles/College/2nd year college/1st Term/Data Structures & Algorithms/Programming/HomeworkShoppingList/ShoppingList.txt"));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 4) {
	                    String itemName = parts[0];
	                    String itemDescription = parts[1];
	                    double itemPrice = Double.parseDouble(parts[2]);
	                    boolean purchased = Boolean.parseBoolean(parts[3]);
	                    ShoppingListItem newItem = new ShoppingListItem(itemName, itemDescription, itemPrice);
	                    newItem.purchased = purchased;
	                    addItem(itemName, itemDescription, itemPrice);
	                }
	            }
	            reader.close();
	            System.out.println("Shopping list loaded from file.");
	        } catch (FileNotFoundException e) {
	            System.out.println("The file 'ShoppingList.txt' does not exist. Do you want to create a new shopping list? (Y/N)");
	            Scanner scanner = new Scanner(System.in);
	            String choice = scanner.nextLine();
	            if (choice.equalsIgnoreCase("Y")) {
	                // Create a new shopping list
	                System.out.println("New shopping list created.");
	            } else {
	                System.out.println("Thank you for using the shopping list.");
	                System.exit(0); // Exit the program
	            }
	        } catch (IOException e) {
	            System.out.println("Error while reading the shopping list file.");
	        }
	    }

}   