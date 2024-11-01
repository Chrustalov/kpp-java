package shop.Serializers.Yaml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Models.Supplier;
import shop.Data.Products;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlDeserializerTest {
    private YamlDeserializer yamlDeserializer;
    private YamlSerializer yamlSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        yamlDeserializer = new YamlDeserializer();
        yamlSerializer = new YamlSerializer();
        tempFile = File.createTempFile("products", ".yaml");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException {
        List<Product> originalProducts = Products.getProducts();

        yamlSerializer.serialize(originalProducts, tempFile.getPath());

        List<Product> deserializedProducts = yamlDeserializer.deserialize(tempFile.getPath());

        assertNotNull(deserializedProducts);
        assertEquals(originalProducts.size(), deserializedProducts.size());

        for (int i = 0; i < originalProducts.size(); i++) {
            Product originalProduct = originalProducts.get(i);
            Product deserializedProduct = deserializedProducts.get(i);

            assertEquals(originalProduct.getName(), deserializedProduct.getName());
            assertEquals(originalProduct.getPrice(), deserializedProduct.getPrice());
            assertEquals(originalProduct.getQuantity(), deserializedProduct.getQuantity());

            List<Supplier> originalSuppliers = originalProduct.getSuppliers();
            List<Supplier> deserializedSuppliers = deserializedProduct.getSuppliers();

            if (originalSuppliers != null) {
                assertEquals(originalSuppliers.size(), deserializedSuppliers.size());
                for (int j = 0; j < originalSuppliers.size(); j++) {
                    assertEquals(originalSuppliers.get(j).getCompanyName(), deserializedSuppliers.get(j).getCompanyName());
                    assertNull(deserializedSuppliers.get(j).getContractStartDate());
                }
            } else {
                assertNull(deserializedSuppliers);
            }
        }
    }

    @Test
    void testDeserializeEmptyFile() throws IOException {
        File emptyFile = File.createTempFile("empty_products", ".yaml");
        emptyFile.deleteOnExit();

        List<Product> products = yamlDeserializer.deserialize(emptyFile.getPath());
        assertNull(products);
    }
}
