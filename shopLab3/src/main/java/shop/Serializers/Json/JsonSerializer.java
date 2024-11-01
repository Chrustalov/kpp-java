package shop.Serializers.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shop.Models.Product;
import shop.Serializers.ISerialize;
import shop.Serializers.LocalDateAdapter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JsonSerializer implements ISerialize<Product> {
    private final Gson gson;

    public JsonSerializer() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void serialize(List<Product> products, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            this.gson.toJson(products, file);
            System.out.println("Products serialized to JSON " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
