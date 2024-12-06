package webscrapping.workers;

import org.junit.jupiter.api.Test;
import webscraping.models.ScrappingResult;
import webscraping.utils.Seeds;
import webscraping.workers.ScraperWorker;

import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.*;

public class ScraperWorkerTest {

    @Test
    public void testScraperWorkerSuccess() throws Exception {
        String testUrl = Seeds.POPULAR_SITES.getFirst();
        ScraperWorker worker = new ScraperWorker(testUrl);

        FutureTask<ScrappingResult> task = new FutureTask<>(worker);
        new Thread(task).start();
        ScrappingResult result = task.get();

        assertEquals("SUCCESS", result.status());
        assertEquals(testUrl, result.url());
        assertNotNull(result.title());
        assertNotNull(result.bodyText());
        assertTrue(result.dataSize() > 0);
    }

    @Test
    public void testScraperWorkerFailure() throws Exception {
        String testUrl = "http://invalid-url";
        ScraperWorker worker = new ScraperWorker(testUrl);

        FutureTask<ScrappingResult> task = new FutureTask<>(worker);
        new Thread(task).start();
        ScrappingResult result = task.get();

        assertEquals("Failed", result.status());
        assertEquals(testUrl, result.url());
        assertEquals("", result.title());
        assertEquals("", result.bodyText());
        assertEquals(0, result.dataSize());
    }

    @Test
    public void testScraperWorkerTimeout() throws Exception {
        String testUrl = Seeds.POPULAR_SITES.get(1);
        ScraperWorker worker = new ScraperWorker(testUrl);

        FutureTask<ScrappingResult> task = new FutureTask<>(worker);
        new Thread(task).start();
        ScrappingResult result = task.get();

        assertNotEquals("Timeout", result.status());
    }

    @Test
    public void testScraperWorkerWithNoMetaTags() throws Exception {
        String testUrl = Seeds.POPULAR_SITES.get(2);
        ScraperWorker worker = new ScraperWorker(testUrl);

        FutureTask<ScrappingResult> task = new FutureTask<>(worker);
        new Thread(task).start();
        ScrappingResult result = task.get();

        assertEquals("SUCCESS", result.status());
        assertEquals(testUrl, result.url());
        assertNotNull(result.title());
    }

    @Test
    public void testScraperWorkerWithLargePage() throws Exception {
        String testUrl = Seeds.POPULAR_SITES.get(3);
        ScraperWorker worker = new ScraperWorker(testUrl);

        FutureTask<ScrappingResult> task = new FutureTask<>(worker);
        new Thread(task).start();
        ScrappingResult result = task.get();

        assertEquals("SUCCESS", result.status());
        assertEquals(testUrl, result.url());
        assertTrue(result.dataSize() > 5000);
    }
}