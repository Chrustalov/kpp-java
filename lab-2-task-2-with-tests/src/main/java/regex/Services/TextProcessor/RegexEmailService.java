package regex.Services.TextProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEmailService implements ITextProcessor {
  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"
  );
  
  @Override
  public List<String> processText(String text) {
      String[] sentences = text.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s+");
      List<String> firstWords = new ArrayList<>();

      for (String sentence : sentences) {
          Matcher matcher = EMAIL_PATTERN.matcher(sentence);
          if (matcher.find()) {
              String firstWord = sentence.split("\\s+")[0];
              firstWords.add(firstWord);
          }
      }
      
      return firstWords;
  }
}
