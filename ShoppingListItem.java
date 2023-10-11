package Lab1Finals;

public class ShoppingListItem {
	 	String itemName;
	    String itemDescription;
	    double itemPrice;
	    boolean purchased;
	    ShoppingListItem next;

	    public ShoppingListItem(String itemName, String itemDescription, double itemPrice) {
	        this.itemName = itemName;
	        this.itemDescription = itemDescription;
	        this.itemPrice = itemPrice;
	        this.purchased = false;
	        this.next = null;
	    }
}
