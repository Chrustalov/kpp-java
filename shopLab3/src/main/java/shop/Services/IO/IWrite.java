package shop.Services.IO;

import shop.Models.Product;

import java.util.List;

public interface IWrite {
    public void writeToFile(List<Product> products, String filePath);
}
