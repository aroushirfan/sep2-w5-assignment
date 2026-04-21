package cart;

public class ShoppingCartCalculator {

    private ShoppingCartCalculator() {
        // Private constructor to prevent instantiation
    }

    public static double calculateItemCost(double price, int quantity) {
        return price * quantity;
    }

    public static double addToTotal(double currentTotal, double itemCost) {
        return currentTotal + itemCost;
    }
}
