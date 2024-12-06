package shop;

import shop.Models.Product;
import shop.Models.Supplier;
import shop.Data.FilePath;
import shop.Data.Products;
import shop.Views.ProductsView;
import shop.Views.SerializersView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Products.getProducts();
        ProductsView productsView = new ProductsView();
        SerializersView serializersView = new SerializersView();

        productsView.writeProductsToFile(products);
        List<Product> readProducts = productsView.readProductsFromFile();

        productsView.writeSuppliersToFile(products);
        List<Supplier> suppliers = productsView.readSuppliersFromFile();

        serializersView.serializeProductsNative(products, FilePath.PRODUCTS_FILE_NATIVE);
        List<Product> nativeDeserializedProducts = serializersView.deserializeProductsNative(FilePath.PRODUCTS_FILE_NATIVE);
        System.out.println("\nNative Deserialized Products:");
        nativeDeserializedProducts.forEach(System.out::println);

        serializersView.serializeProductsJson(products, FilePath.PRODUCTS_FILE_JSON);
        List<Product> jsonDeserializedProducts = serializersView.deserializeProductsJson(FilePath.PRODUCTS_FILE_JSON);
        System.out.println("\nJSON Deserialized Products:");
        jsonDeserializedProducts.forEach(System.out::println);

        serializersView.serializeProductsYaml(products, FilePath.PRODUCTS_FILE_YAML);
        List<Product> yamlDeserializedProducts = serializersView.deserializeProductsYaml(FilePath.PRODUCTS_FILE_YAML);
        System.out.println("\nYAML Deserialized Products:");
        yamlDeserializedProducts.forEach(System.out::println);
    }
}
