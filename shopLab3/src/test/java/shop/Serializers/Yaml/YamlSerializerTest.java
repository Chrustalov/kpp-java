package shop.Serializers.Yaml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Models.Supplier;
import shop.Data.Products;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import static org.junit.jupiter.api.Assertions.*;

class YamlSerializerTest {
    private YamlSerializer yamlSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        yamlSerializer = new YamlSerializer();
        tempFile = File.createTempFile("products", ".yaml");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerializeWithSuppliersWithoutDate() throws IOException {
        List<Product> products = Products.getProducts();

        yamlSerializer.serialize(products, tempFile.getPath());

        try (FileReader reader = new FileReader(tempFile)) {
            Yaml yaml = new Yaml();
            List<Product> deserializedProducts = yaml.load(reader);

            assertNotNull(deserializedProducts);
            assertEquals(products.size(), deserializedProducts.size());

            for (int i = 0; i < products.size(); i++) {
                Product originalProduct = products.get(i);
                Product deserializedProduct = deserializedProducts.get(i);

                assertEquals(originalProduct.getName(), deserializedProduct.getName());
                assertEquals(originalProduct.getPrice(), deserializedProduct.getPrice());
                assertEquals(originalProduct.getQuantity(), deserializedProduct.getQuantity());

                for (int j = 0; j < originalProduct.getSuppliers().size(); j++) {
                    Supplier originalSupplier = originalProduct.getSuppliers().get(j);
                    Supplier deserializedSupplier = deserializedProduct.getSuppliers().get(j);

                    assertEquals(originalSupplier.getCompanyName(), deserializedSupplier.getCompanyName());
                    assertNull(deserializedSupplier.getContractStartDate(), "Поле contractStartDate має бути null");
                }
            }
        }
    }

    @Test
    void testSerializeEmptyList() throws IOException {
        List<Product> products = List.of();
        yamlSerializer.serialize(products, tempFile.getPath());

        try (FileReader reader = new FileReader(tempFile)) {
            Yaml yaml = new Yaml();
            List<?> deserializedProducts = yaml.load(reader);

            assertNotNull(deserializedProducts, "Список має бути пустим");
            assertTrue(deserializedProducts.isEmpty(), "Десеріалізовані дані мають бути порожнім списком");
        }
    }
}
