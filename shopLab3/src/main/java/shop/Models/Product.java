package shop.Models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Product implements Serializable {
    @Expose
    private String name;
    private double price;
    @Expose
    private int quantity;
    @Expose
    private List<Supplier> suppliers;

    public Product() {}

    public Product(String name, double price, int quantity, List<Supplier> suppliers) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.suppliers = suppliers;
    }

    // Геттери та сеттери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", suppliers=" + suppliers +
                '}';
    }
}
