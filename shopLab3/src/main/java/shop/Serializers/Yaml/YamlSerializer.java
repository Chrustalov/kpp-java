package shop.Serializers.Yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import shop.Models.Product;
import shop.Models.Supplier;
import shop.Serializers.ISerialize;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class YamlSerializer implements ISerialize<Product> {
    private final Yaml yaml;

    public YamlSerializer() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setIndent(2);
        this.yaml = new Yaml(options);
    }

    @Override
    public void serialize(List<Product> products, String filePath) {
        List<Product> filteredProducts = products.stream()
                .map(this::filterSuppliersWithoutDate)
                .collect(Collectors.toList());

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(filteredProducts, writer);
            System.out.println("Products serialized to YAML file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product filterSuppliersWithoutDate(Product product) {
        List<Supplier> suppliersWithoutDate = product.getSuppliers().stream()
                .map(supplier -> new Supplier(supplier.getCompanyName(), null))
                .collect(Collectors.toList());

        return new Product(product.getName(), product.getPrice(), product.getQuantity(), suppliersWithoutDate);
    }
}
