package webscraping.controllers;

import webscraping.models.ScrappingResult;
import webscraping.utils.Logger;
import webscraping.workers.ScraperWorker;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadsController {
    private final ExecutorService threadPool;
    private final CompletionService<ScrappingResult> completionService;
    private final BlockingQueue<String> urlQueue;
    private final JPanel resultPanel;
    private final JLabel statusLabel;
    private final AtomicLong totalDataSize = new AtomicLong(0);
    private final AtomicLong totalDuration = new AtomicLong(0);

    public ThreadsController(Integer numThreads, List<String> urls, JPanel resultPanel, JLabel statusLabel) {
        this.threadPool = Executors.newFixedThreadPool(numThreads);
        this.completionService = new ExecutorCompletionService<>(threadPool);
        this.urlQueue = new LinkedBlockingQueue<>(urls);
        this.resultPanel = resultPanel;
        this.statusLabel = statusLabel;
    }

    public void startScraping() {
        int totalUrls = urlQueue.size();

        while (!urlQueue.isEmpty()) {
            try {
                String url = urlQueue.take();
                completionService.submit(new ScraperWorker(url));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int remainingTasks = totalUrls;
        long startTime = System.currentTimeMillis();

        while (remainingTasks > 0) {
            try {
                Future<ScrappingResult> future = completionService.take();
                ScrappingResult result = future.get();

                totalDataSize.addAndGet(result.dataSize());
                totalDuration.addAndGet(result.duration());

                String logMessage = "URL: " + result.url() + ", Status: " + result.status() + ", Duration: " + result.duration() + " ms, Data Size: " + result.dataSize() + " characters" + ", Title: " + result.title() + ", Description: " + result.description() + ", Keywords: " + result.keywords() + ", Body Text: " + result.bodyText();
                Logger.log(logMessage);

                JPanel taskPanel = new JPanel();
                taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
                taskPanel.setBorder(BorderFactory.createTitledBorder("Thread: " + result.threadName()));
                taskPanel.add(new JLabel("URL: " + result.url()));
                taskPanel.add(new JLabel("Status: " + result.status()));
                taskPanel.add(new JLabel("Duration: " + result.duration() + " ms"));
                taskPanel.add(new JLabel("Data Size: " + result.dataSize() + " characters"));
                taskPanel.add(new JLabel("Title: " + result.title()));
                taskPanel.add(new JLabel("Description: " + result.description()));
                taskPanel.add(new JLabel("Keywords: " + result.keywords()));
                taskPanel.add(new JLabel("Body Text: " + result.bodyText()));

                resultPanel.add(taskPanel);
                resultPanel.revalidate();
                resultPanel.repaint();

                remainingTasks--;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long totalElapsedTime = endTime - startTime;
        String summary = "Total scraping time: " + totalElapsedTime + " ms, Total data size: " + totalDataSize.get() + " characters";
        Logger.log(summary);

        statusLabel.setText(summary);

        threadPool.shutdown();
    }
}
