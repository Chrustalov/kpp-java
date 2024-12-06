package webscraping.workers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import webscraping.models.ScrappingResult;
import webscraping.utils.Logger;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

public class ScraperWorker implements Callable<ScrappingResult> {
    private final String url;

    public ScraperWorker(String url) {
        this.url = url;
    }

    @Override
    public ScrappingResult call() {
        long startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();

        try {
            Document document = Jsoup.connect(url)
                    .timeout(5000)
                    .get();

            String title = document.title();
            String description = document.select("meta[name=description]").attr("content");
            String keywords = document.select("meta[name=keywords]").attr("content");
            String bodyText = document.body().text();

            long duration = System.currentTimeMillis() - startTime;
            return new ScrappingResult(threadName, url, duration, bodyText.length(), "SUCCESS", title, description, keywords, bodyText);
        } catch (Exception e) {
            Logger.log(MessageFormat.format("Error on URL {0}: {1}", url, e.getMessage()));
            long duration = System.currentTimeMillis() - startTime;
            String status = e instanceof java.net.SocketTimeoutException ? "Timeout" : "Failed";
            return new ScrappingResult(threadName, url, duration, 0, status, "", "", "", "");
        }
    }
}
