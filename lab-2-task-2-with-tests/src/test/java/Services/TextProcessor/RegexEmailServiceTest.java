package Services.TextProcessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import regex.Services.TextProcessor.ITextProcessor;
import regex.Services.TextProcessor.RegexEmailService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegexEmailServiceTest {

    private ITextProcessor textProcessor;

    @BeforeEach
    public void setUp() {
        textProcessor = new RegexEmailService();
    }

    @Test
    public void testProcessTextWithEmails() {
        String text = "Contact me at john.doe@example.com. Please email support@company.com for assistance.";

        List<String> result = textProcessor.processText(text);

        assertEquals(2, result.size());
        assertEquals("Contact", result.get(0));
        assertEquals("Please", result.get(1));
    }

    @Test
    public void testProcessTextWithoutEmails() {
        String text = "This is a test sentence. No emails here.";

        List<String> result = textProcessor.processText(text);

        assertEquals(0, result.size());
    }

    @Test
    public void testProcessTextWithMixedSentences() {
        String text = "Hello world. Contact john.doe@example.com. Visit our website.";

        List<String> result = textProcessor.processText(text);

        assertEquals(1, result.size());
        assertEquals("Contact", result.get(0));
    }

    @Test
    public void testProcessTextWithMultipleEmailsInOneSentence() {
        String text = "Send emails to john.doe@example.com and jane.doe@another.com.";

        List<String> result = textProcessor.processText(text);

        assertEquals(1, result.size());
        assertEquals("Send", result.get(0));
    }

    @Test
    public void testProcessTextWithEmptyString() {
        String text = "";

        List<String> result = textProcessor.processText(text);

        assertEquals(0, result.size());
    }
}
