package shop.Serializers.Json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import shop.Models.Product;
import shop.Serializers.IDeserialize;
import shop.Serializers.LocalDateAdapter;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonDeserializer implements IDeserialize<Product> {
    private final Gson gson;

    public JsonDeserializer() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public List<Product> deserialize(String filePath) {
        try (FileReader file = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
            List<Product> products = gson.fromJson(file, listType);
            System.out.println("Products deserialized from JSON: " + filePath);
            return products;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
