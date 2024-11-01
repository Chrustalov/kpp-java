package shop.Serializers;

import java.util.List;

public interface IDeserialize<T> {
    List<T> deserialize(String filePath);
}
