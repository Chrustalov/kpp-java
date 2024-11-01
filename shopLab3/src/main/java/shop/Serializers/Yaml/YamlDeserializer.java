package shop.Serializers.Yaml;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import shop.Models.Product;
import shop.Models.Supplier;
import shop.Serializers.IDeserialize;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class YamlDeserializer implements IDeserialize<Product> {
    private final Yaml yaml;

    public YamlDeserializer() {
        // Налаштовуємо конструктор для списку продуктів
        Constructor constructor = new Constructor(List.class);

        // Описуємо тип Product і вказуємо, що suppliers - це список Supplier
        TypeDescription productDesc = new TypeDescription(Product.class);
        productDesc.addPropertyParameters("suppliers", Supplier.class);

        // Додаємо опис типу для Product
        constructor.addTypeDescription(productDesc);

        // Ініціалізуємо Yaml з налаштованим конструктором
        this.yaml = new Yaml(constructor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> deserialize(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            List<Product> products = (List<Product>) yaml.load(reader);
            System.out.println("Products deserialized from YAML file: " + filePath);
            return products;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
