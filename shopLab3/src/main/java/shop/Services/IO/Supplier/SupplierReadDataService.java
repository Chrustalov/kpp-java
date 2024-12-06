package shop.Services.IO.Supplier;

import shop.Models.Supplier;
import shop.Services.IO.IRead;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class SupplierReadDataService implements IRead<Supplier> {
    @Override
    public List<Supplier> ReadFromFile(String filename) {
        List<Supplier> suppliers = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            suppliers = (List<Supplier>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
