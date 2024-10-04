package Services.FileReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import regex.Services.FileReader.IFileReader;
import regex.Services.FileReader.SimpleFileReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleFileReaderServiceTest {

  private IFileReader fileReaderService;

  @BeforeEach
  public void setUp() {
    fileReaderService = new SimpleFileReaderService();
  }

  @Test
  public void testReadValidFile() throws IOException {
    Path tempFile = Files.createTempFile("testFile", ".txt");
    String expectedContent = "This is a test file.";
    Files.writeString(tempFile, expectedContent);

    String actualContent = fileReaderService.read(tempFile.toString());

    assertEquals(expectedContent, actualContent);

    Files.deleteIfExists(tempFile);
  }

  @Test
  public void testReadNonExistentFile() {
    String invalidFilePath = "non_existent_file.txt";
    assertThrows(IOException.class, () -> fileReaderService.read(invalidFilePath));
  }
}
