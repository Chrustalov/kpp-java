package regex.Services.FileReader;

import java.io.IOException;

public interface IFileReader {
  String read(String filePath) throws IOException;
}
