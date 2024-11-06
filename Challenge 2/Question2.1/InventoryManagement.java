import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {
    public static void main(String[] args) {
        List < Product > products = new ArrayList < > ();
        products.add(new Product("Laptop", 999.99, 5));
        products.add(new Product("Smartphone", 499.99, 10));
        products.add(new Product("Tablet", 299.99, 0));
        products.add(new Product("Smartwatch", 199.99, 3));

        // Perform tasks
        System.out.println("Total Inventory Value: " + calculateTotalInventoryValue(products));
        System.out.println("Most Expensive Product: " + findMostExpensiveProduct(products));
        System.out.println("Is 'Headphones' in stock? " + isProductInStock(products, "Headphones"));
        System.out.println("Products sorted by price (ascending): " + sortProductsByPrice(products, true));
        System.out.println("Products sorted by quantity (descending): " + sortProductsByQuantity(products, false));
    }

    // Step 3: Calculate the total inventory value
    public static double calculateTotalInventoryValue(List < Product > products) {
        double totalValue = 0;
        for (Product product: products) {
            totalValue += product.getTotalValue();
        }

        totalValue = Math.round(totalValue * 100.0) / 100.0;

        return totalValue;
    }


    // Step 4: Find the most expensive product
    public static String findMostExpensiveProduct(List < Product > products) {
        Product mostExpensive = null;
        for (Product product: products) {
            if (mostExpensive == null || product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive != null ? mostExpensive.getName() : null;
    }

    // Step 5: Check if a product named "Headphones" is in stock
    public static boolean isProductInStock(List < Product > products, String productName) {
        for (Product product: products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Step 6: Sort products by price
    public static List < String > sortProductsByPrice(List < Product > products, boolean ascending) {
        products.sort((p1, p2) - > ascending ? Double.compare(p1.getPrice(), p2.getPrice()) : Double.compare(p2.getPrice(), p1.getPrice()));

        List < String > sortedByPrice = new ArrayList < > ();
        for (Product product: products) {
            sortedByPrice.add(product.getName() + " - $" + product.getPrice());
        }
        return sortedByPrice;
    }

    // Step 6: Sort products by quantity
    public static List < String > sortProductsByQuantity(List < Product > products, boolean ascending) {
        products.sort((p1, p2) - > ascending ? Integer.compare(p1.getQuantity(), p2.getQuantity()) : Integer.compare(p2.getQuantity(), p1.getQuantity()));

        List < String > sortedByQuantity = new ArrayList < > ();
        for (Product product: products) {
            sortedByQuantity.add(product.getName() + " - " + product.getQuantity() + " units");
        }
        return sortedByQuantity;
    }
}