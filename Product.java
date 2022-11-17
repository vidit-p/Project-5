/**
 * Project 4 -- Product class
 * <p>
 * Class for storing information about a product
 * for a digital marketplace
 *
 * @author Tingxuan "Lisa" Huang, CS 18000-L19 LAB - 5
 * @version November 6, 2022
 */
public class Product {
    private int number;
    private String name;
    private String storeName;
    private String description;
    private int quantity;
    private double price;
    private String sellerName;

    public Product(int number, String name, String storeName, String description, int quantity,
                   double price, String sellerName) {
        this.number = number;
        this.name = name;
        this.storeName = storeName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.sellerName = sellerName;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}