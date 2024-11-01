package shop.Services.IO.Product;

import shop.Models.Product;
import shop.Services.IO.IRead;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductReadDataService implements IRead<Product> {
    @Override
    public List<Product> ReadFromFile(String filePath) {
        List<Product> products = new ArrayList<>();

        try (DataInputStream in = new DataInputStream(new FileInputStream(filePath))) {
            int size = in.readInt();

            for (int i = 0; i < size; i++) {
                String name = in.readUTF();
                int quantity = in.readInt();
                double price = in.readDouble();
                products.add(new Product(name, price, quantity, new ArrayList<>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
