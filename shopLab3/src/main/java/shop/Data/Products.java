package shop.Data;

import shop.Models.Product;
import shop.Models.Supplier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Products {
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        List<Supplier> suppliers1 = List.of(
                new Supplier("Supplier A", LocalDate.of(2023, 1, 15)),
                new Supplier("Supplier B", LocalDate.of(2023, 5, 20))
        );

        List<Supplier> suppliers2 = List.of(
                new Supplier("Supplier C", LocalDate.of(2023, 2, 10))
        );

        List<Supplier> suppliers3 = List.of(
                new Supplier("Supplier D", LocalDate.of(2023, 3, 5)),
                new Supplier("Supplier E", LocalDate.of(2023, 4, 12))
        );

        products.add(new Product("Laptop", 1200.0, 10, suppliers1));
        products.add(new Product("Smartphone", 800.0, 20, suppliers2));
        products.add(new Product("Tablet", 400.0, 15, suppliers3));
        products.add(new Product("Headphones", 150.0, 30, suppliers1));
        products.add(new Product("Smartwatch", 200.0, 25, suppliers2));
        products.add(new Product("Monitor", 300.0, 18, suppliers3));
        products.add(new Product("Keyboard", 50.0, 50, suppliers1));
        products.add(new Product("Mouse", 30.0, 60, suppliers2));
        products.add(new Product("Printer", 150.0, 12, suppliers3));
        products.add(new Product("Camera", 450.0, 8, suppliers1));

        return products;
    }
}
