package shop.Serializers.Native;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Data.Products;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NativeSerializerTest {
    private NativeSerializer nativeSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        nativeSerializer = new NativeSerializer();
        tempFile = File.createTempFile("products", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerializeAndDeserializeWithoutSuppliers() throws IOException, ClassNotFoundException {
        List<Product> originalProducts = Products.getProducts();

        nativeSerializer.serialize(originalProducts, tempFile.getPath());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            for (Product originalProduct : originalProducts) {
                Product deserializedProduct = (Product) ois.readObject();

                assertEquals(originalProduct.getName(), deserializedProduct.getName());
                assertEquals(originalProduct.getPrice(), deserializedProduct.getPrice());
                assertEquals(originalProduct.getQuantity(), deserializedProduct.getQuantity());
                assertNull(deserializedProduct.getSuppliers(), "Поле suppliers має бути null після серіалізації");
            }
        }
    }
}
