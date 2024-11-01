package shop.Services.IO.Product;

import shop.Models.Product;
import shop.Services.IO.IWrite;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProductWriteDataService implements IWrite {
    @Override
    public void writeToFile(List<Product> products, String filePath) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath))) {
            out.writeInt(products.size());

            for (Product product : products) {
                out.writeUTF(product.getName());
                out.writeInt(product.getQuantity());
                out.writeDouble(product.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
