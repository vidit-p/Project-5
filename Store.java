import java.util.ArrayList;

/**
 * Store class
 *
 *
 *
 * @author Vidit Patel
 * @version November 14, 2022
 */

public class Store {
    private String name;
    private String owner;
    private ArrayList<Product> products;
    private double revenue;

    public Store(String name, String owner, ArrayList<Product> products, double revenue) {
        this.name = name;
        this.owner = owner;
        this.products = products;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}