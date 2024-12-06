package webscrapping.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webscraping.controllers.ThreadsController;
import webscraping.utils.Seeds;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadsControllerTest {

    private ThreadsController controller;
    private JPanel resultPanel;
    private JLabel statusLabel;
    private List<String> urls;

    @BeforeEach
    public void setUp() {
        resultPanel = new JPanel();
        statusLabel = new JLabel();
        urls = Seeds.POPULAR_SITES;
        controller = new ThreadsController(2, urls, resultPanel, statusLabel);
    }

    @Test
    public void testStartScraping() {
        controller.startScraping();

        assertEquals(urls.size(), resultPanel.getComponentCount());
        assertTrue(statusLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testEmptyUrlList() {
        controller = new ThreadsController(2, List.of(), resultPanel, statusLabel);
        controller.startScraping();

        assertEquals(0, resultPanel.getComponentCount());
        assertTrue(statusLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testInvalidUrl() {
        urls = List.of("http://invalid-url");
        controller = new ThreadsController(2, urls, resultPanel, statusLabel);
        controller.startScraping();

        assertEquals(1, resultPanel.getComponentCount());
        assertTrue(statusLabel.getText().contains("Total scraping time"));
    }
}