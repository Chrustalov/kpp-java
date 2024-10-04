package regex;

import java.util.*;
import java.io.IOException;

import regex.Services.FileReader.IFileReader;
import regex.Services.FileReader.SimpleFileReaderService;
import regex.Services.TextProcessor.ITextProcessor;
import regex.Services.TextProcessor.RegexEmailService;

public class Main {
    public static void main(String[] args) {
        String filePath = "text.txt";

        IFileReader fileReader = new SimpleFileReaderService();
        ITextProcessor textProcessor = new RegexEmailService();

        try {
            String content = fileReader.read(filePath);
            List<String> result = textProcessor.processText(content);

            result.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
