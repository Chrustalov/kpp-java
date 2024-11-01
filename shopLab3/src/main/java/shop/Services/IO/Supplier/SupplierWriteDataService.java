package shop.Services.IO.Supplier;

import shop.Models.Supplier;
import shop.Models.Product;
import shop.Services.IO.IWrite;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierWriteDataService implements IWrite {
    @Override
    public void writeToFile(List<Product> products, String filePath) {
        List<Supplier> suppliers = products.stream()
                .flatMap(product -> product.getSuppliers().stream())
                .collect(Collectors.toList());

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(suppliers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
