package shop.Services.IO.Supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Product;
import shop.Models.Supplier;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierWriteDataServiceTest {
    private SupplierWriteDataService supplierWriteDataService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        supplierWriteDataService = new SupplierWriteDataService();
        tempFile = File.createTempFile("suppliers", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testWriteToFile() throws IOException, ClassNotFoundException {
        List<Product> products = List.of(
                new Product("Laptop", 1200.0, 10, List.of(
                        new Supplier("Supplier A", LocalDate.of(2023, 1, 15)),
                        new Supplier("Supplier B", LocalDate.of(2022, 5, 10))
                )),
                new Product("Phone", 800.0, 5, List.of(
                        new Supplier("Supplier C", LocalDate.of(2021, 8, 20))
                ))
        );

        supplierWriteDataService.writeToFile(products, tempFile.getPath());

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(tempFile)))) {
            List<Supplier> suppliers = (List<Supplier>) ois.readObject();
            assertNotNull(suppliers, "Список постачальників не має бути null");
            assertEquals(3, suppliers.size(), "Кількість постачальників не співпадає з очікуваною");

            Supplier supplier1 = suppliers.get(0);
            assertEquals("Supplier A", supplier1.getCompanyName());
            assertEquals(LocalDate.of(2023, 1, 15), supplier1.getContractStartDate());

            Supplier supplier2 = suppliers.get(1);
            assertEquals("Supplier B", supplier2.getCompanyName());
            assertEquals(LocalDate.of(2022, 5, 10), supplier2.getContractStartDate());

            Supplier supplier3 = suppliers.get(2);
            assertEquals("Supplier C", supplier3.getCompanyName());
            assertEquals(LocalDate.of(2021, 8, 20), supplier3.getContractStartDate());
        }
    }
}
