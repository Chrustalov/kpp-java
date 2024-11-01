package shop.Serializers.Native;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Data.Products;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NativeDeserializerTest {
    private NativeDeserializer nativeDeserializer;
    private NativeSerializer nativeSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        nativeDeserializer = new NativeDeserializer();
        nativeSerializer = new NativeSerializer();
        tempFile = File.createTempFile("products", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException {
        List<Product> originalProducts = Products.getProducts();

        originalProducts.forEach(product -> product.setSuppliers(null));
        nativeSerializer.serialize(originalProducts, tempFile.getPath());

        List<Product> deserializedProducts = nativeDeserializer.deserialize(tempFile.getPath());

        assertEquals(originalProducts.size(), deserializedProducts.size());
        for (int i = 0; i < originalProducts.size(); i++) {
            assertEquals(originalProducts.get(i).getName(), deserializedProducts.get(i).getName());
            assertEquals(originalProducts.get(i).getPrice(), deserializedProducts.get(i).getPrice());
            assertEquals(originalProducts.get(i).getQuantity(), deserializedProducts.get(i).getQuantity());

            assertNull(deserializedProducts.get(i).getSuppliers(), "Поле suppliers має бути null після серіалізації");
        }
    }
}
