package shop.Views;

import shop.Models.Product;
import shop.Serializers.Json.JsonDeserializer;
import shop.Serializers.Json.JsonSerializer;
import shop.Serializers.Native.NativeDeserializer;
import shop.Serializers.Native.NativeSerializer;
import shop.Serializers.Yaml.YamlDeserializer;
import shop.Serializers.Yaml.YamlSerializer;

import java.util.List;

public class SerializersView {
    private final NativeSerializer nativeSerializer;
    private final NativeDeserializer nativeDeserializer;
    private final JsonSerializer jsonSerializer;
    private final JsonDeserializer jsonDeserializer;
    private final YamlSerializer yamlSerializer;
    private final YamlDeserializer yamlDeserializer;

    public SerializersView() {
        this.nativeSerializer = new NativeSerializer();
        this.nativeDeserializer = new NativeDeserializer();
        this.jsonSerializer = new JsonSerializer();
        this.jsonDeserializer = new JsonDeserializer();
        this.yamlSerializer = new YamlSerializer();
        this.yamlDeserializer = new YamlDeserializer();
    }

    public void serializeProductsNative(List<Product> products, String filePath) {
        nativeSerializer.serialize(products, filePath);
    }

    public List<Product> deserializeProductsNative(String filePath) {
        return nativeDeserializer.deserialize(filePath);
    }

    public void serializeProductsJson(List<Product> products, String filePath) {
        jsonSerializer.serialize(products, filePath);
    }

    public List<Product> deserializeProductsJson(String filePath) {
        return jsonDeserializer.deserialize(filePath);
    }

    public void serializeProductsYaml(List<Product> products, String filePath) {
        yamlSerializer.serialize(products, filePath);
    }

    public List<Product> deserializeProductsYaml(String filePath) {
        return yamlDeserializer.deserialize(filePath);
    }
}
