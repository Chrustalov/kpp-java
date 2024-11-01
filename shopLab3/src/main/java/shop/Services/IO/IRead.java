package shop.Services.IO;

import java.util.List;

public interface IRead<T> {
    List<T> ReadFromFile(String filename);
}
