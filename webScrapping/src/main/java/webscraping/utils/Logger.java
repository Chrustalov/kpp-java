package webscraping.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

public class Logger {
    private static final String LOG_FILE = "scraping_results.log";

    public static void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println(MessageFormat.format("Error writing to log file: {0}", e.getMessage()));
        }
    }
}
