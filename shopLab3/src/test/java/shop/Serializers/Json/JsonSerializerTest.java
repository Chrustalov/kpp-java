package shop.Serializers.Json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Models.Supplier;
import shop.Data.Products;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonSerializerTest {
    private JsonSerializer jsonSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        jsonSerializer = new JsonSerializer();
        tempFile = File.createTempFile("products", ".json");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerialize() throws IOException {
        List<Product> products = Products.getProducts();

        jsonSerializer.serialize(products, tempFile.getPath());
        assertTrue(tempFile.exists(), "Файл не був створений");
        assertTrue(tempFile.length() > 0, "Файл порожній після серіалізації");
    }
}
