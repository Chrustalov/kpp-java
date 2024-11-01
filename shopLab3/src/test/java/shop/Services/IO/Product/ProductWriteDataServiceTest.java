package shop.Services.IO.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductWriteDataServiceTest {
    private ProductWriteDataService productWriteDataService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        productWriteDataService = new ProductWriteDataService();
        tempFile = File.createTempFile("products", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testWriteToFile() throws IOException {
        List<Product> products = List.of(
                new Product("Laptop", 1200.0, 10, null),
                new Product("Phone", 600.0, 5, null)
        );

        productWriteDataService.writeToFile(products, tempFile.getPath());

        try (DataInputStream in = new DataInputStream(new FileInputStream(tempFile))) {
            int size = in.readInt();
            assertEquals(2, size);

            String name1 = in.readUTF();
            int quantity1 = in.readInt();
            double price1 = in.readDouble();
            assertEquals("Laptop", name1);
            assertEquals(10, quantity1);
            assertEquals(1200.0, price1);

            String name2 = in.readUTF();
            int quantity2 = in.readInt();
            double price2 = in.readDouble();
            assertEquals("Phone", name2);
            assertEquals(5, quantity2);
            assertEquals(600.0, price2);
        }
    }
}
