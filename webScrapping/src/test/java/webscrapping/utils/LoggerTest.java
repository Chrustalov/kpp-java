package webscrapping.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webscraping.utils.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private static final String LOG_FILE = "scraping_results.log";

    @BeforeEach
    public void setUp() throws IOException {
        Files.write(Paths.get(LOG_FILE), new byte[0]);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.write(Paths.get(LOG_FILE), new byte[0]);
    }

    @Test
    public void testLogMessage() throws IOException {
        String testMessage = "Test log message";
        Logger.log(testMessage);

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String loggedMessage = reader.readLine();
            assertEquals(testMessage, loggedMessage);
        }
    }

    @Test
    public void testLogMultipleMessages() throws IOException {
        String message1 = "First log message";
        String message2 = "Second log message";
        Logger.log(message1);
        Logger.log(message2);

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            assertEquals(message1, reader.readLine());
            assertEquals(message2, reader.readLine());
        }
    }

    @Test
    public void testLogFileAppend() throws IOException {
        String initialMessage = "Initial log message";
        Logger.log(initialMessage);

        String newMessage = "New log message";
        Logger.log(newMessage);

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            assertEquals(initialMessage, reader.readLine());
            assertEquals(newMessage, reader.readLine());
        }
    }
}