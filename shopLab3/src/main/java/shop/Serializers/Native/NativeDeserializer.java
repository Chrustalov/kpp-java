package shop.Serializers.Native;

import shop.Models.Product;
import shop.Serializers.IDeserialize;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class NativeDeserializer implements IDeserialize<Product> {
    @Override
    public List<Product> deserialize(String filePath) {
        List<Product> products = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Product item = (Product) ois.readObject();
                    products.add(item);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Products deserialized from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return products;
    }
}
