package shop.Services.IO.Supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Models.Supplier;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierReadDataServiceTest {
    private SupplierReadDataService supplierReadDataService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        supplierReadDataService = new SupplierReadDataService();
        tempFile = File.createTempFile("suppliers", ".dat");

        List<Supplier> suppliers = List.of(
                new Supplier("Supplier A", LocalDate.of(2023, 1, 15)),
                new Supplier("Supplier B", LocalDate.of(2022, 5, 10))
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(tempFile)))) {
            oos.writeObject(suppliers);
        }
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testReadFromFile() {
        List<Supplier> suppliers = supplierReadDataService.ReadFromFile(tempFile.getPath());

        assertNotNull(suppliers, "Список постачальників не має бути null");
        assertEquals(2, suppliers.size(), "Кількість постачальників не співпадає з очікуваною");

        Supplier supplier1 = suppliers.get(0);
        assertEquals("Supplier A", supplier1.getCompanyName());
        assertEquals(LocalDate.of(2023, 1, 15), supplier1.getContractStartDate());

        Supplier supplier2 = suppliers.get(1);
        assertEquals("Supplier B", supplier2.getCompanyName());
        assertEquals(LocalDate.of(2022, 5, 10), supplier2.getContractStartDate());
    }
}
