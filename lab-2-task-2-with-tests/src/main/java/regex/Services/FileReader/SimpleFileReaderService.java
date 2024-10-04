package regex.Services.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleFileReaderService implements IFileReader {
  @Override
  public String read(String filePath) throws IOException {
    return Files.readString(Path.of(filePath));
  }
}
