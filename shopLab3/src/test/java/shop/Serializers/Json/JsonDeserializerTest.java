package shop.Serializers.Json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Data.Products;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializerTest {
    private JsonDeserializer jsonDeserializer;
    private JsonSerializer jsonSerializer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        jsonDeserializer = new JsonDeserializer();
        jsonSerializer = new JsonSerializer();
        tempFile = File.createTempFile("products", ".json");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException {
        List<Product> originalProducts = Products.getProducts();
        jsonSerializer.serialize(originalProducts, tempFile.getPath());
        List<Product> deserializedProducts = jsonDeserializer.deserialize(tempFile.getPath());

        assertEquals(originalProducts.size(), deserializedProducts.size());
        for (int i = 0; i < originalProducts.size(); i++) {
            assertEquals(originalProducts.get(i).getName(), deserializedProducts.get(i).getName());
            assertEquals(originalProducts.get(i).getQuantity(), deserializedProducts.get(i).getQuantity());

            for (int j = 0; j < originalProducts.get(i).getSuppliers().size(); j++) {
                assertEquals(originalProducts.get(i).getSuppliers().get(j).getCompanyName(),
                        deserializedProducts.get(i).getSuppliers().get(j).getCompanyName());
                assertEquals(originalProducts.get(i).getSuppliers().get(j).getContractStartDate(),
                        deserializedProducts.get(i).getSuppliers().get(j).getContractStartDate());
            }
        }
    }
}
