package shop.Serializers.Native;

import shop.Models.Product;
import shop.Serializers.ISerialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class NativeSerializer implements ISerialize<Product> {
    @Override
    public void serialize(List<Product> products, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Product product : products) {
                Product productCopy = new Product(
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        null
                );
                oos.writeObject(productCopy);
            }
            System.out.println("Products serialized to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
