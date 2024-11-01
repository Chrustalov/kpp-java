package shop.Services.IO.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductReadDataServiceTest {
    private ProductReadDataService productReadDataService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        productReadDataService = new ProductReadDataService();
        tempFile = File.createTempFile("products", ".dat");

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(tempFile))) {
            out.writeInt(2);
            out.writeUTF("Laptop");
            out.writeInt(10);
            out.writeDouble(1200.0);
            out.writeUTF("Phone");
            out.writeInt(5);
            out.writeDouble(600.0);
        }
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testReadFromFile() {
        List<Product> products = productReadDataService.ReadFromFile(tempFile.getPath());

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals(10, products.get(0).getQuantity());
        assertEquals(1200.0, products.get(0).getPrice());
        assertEquals("Phone", products.get(1).getName());
        assertEquals(5, products.get(1).getQuantity());
        assertEquals(600.0, products.get(1).getPrice());
    }
}
