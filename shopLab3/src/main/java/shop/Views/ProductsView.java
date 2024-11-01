package shop.Views;

import shop.Models.Product;
import shop.Models.Supplier;
import shop.Data.FilePath;
import shop.Services.IO.Product.ProductReadDataService;
import shop.Services.IO.Product.ProductWriteDataService;
import shop.Services.IO.Supplier.SupplierReadDataService;
import shop.Services.IO.Supplier.SupplierWriteDataService;
import shop.Services.IO.IRead;
import shop.Services.IO.IWrite;

import java.util.List;

public class ProductsView {
    private final IWrite productWriteService;
    private final IRead<Product> productReadService;
    private final IWrite supplierWriteService;
    private final IRead<Supplier> supplierReadService;

    public ProductsView() {
        this.productWriteService = new ProductWriteDataService();
        this.productReadService = new ProductReadDataService();
        this.supplierWriteService = new SupplierWriteDataService();
        this.supplierReadService = new SupplierReadDataService();
    }

    public void writeProductsToFile(List<Product> products) {
        productWriteService.writeToFile(products, FilePath.PRODUCTS_FILE);
        System.out.println("Products written to file: " + FilePath.PRODUCTS_FILE);
    }

    public List<Product> readProductsFromFile() {
        List<Product> products = productReadService.ReadFromFile(FilePath.PRODUCTS_FILE);
        System.out.println("\nProducts read from file:");
        for (Product product : products) {
            System.out.println(product);
        }
        return products;
    }

    public void writeSuppliersToFile(List<Product> products) {
        supplierWriteService.writeToFile(products, FilePath.SUPPLIERS_FILE);
        System.out.println("Suppliers written to file: " + FilePath.SUPPLIERS_FILE);
    }

    public List<Supplier> readSuppliersFromFile() {
        List<Supplier> suppliers = supplierReadService.ReadFromFile(FilePath.SUPPLIERS_FILE);
        System.out.println("\nSuppliers read from file:");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
        return suppliers;
    }
}
