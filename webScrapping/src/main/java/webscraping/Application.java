package webscraping;

import webscraping.controllers.ThreadsController;
import webscraping.utils.Seeds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Application extends JFrame {
    private ThreadsController threadsController;
    private JPanel appPanel;
    private JTextField threadsInput;
    private JButton runButton;
    private JTextArea urlsInput;
    private JPanel resultPanel;
    private JLabel statusLabel;

    public Application() {
        setTitle("Web Scraping Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(appPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);

        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        urlsInput.setText(String.join("\n", Seeds.POPULAR_SITES));

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = threadsInput.getText();
                String urlsText = urlsInput.getText();

                try {
                    int numThreads = Integer.parseInt(inputText);
                    List<String> urls = List.of(urlsText.split("\\n"));

                    resultPanel.removeAll();
                    statusLabel.setText("Status: Running...");

                    threadsController = new ThreadsController(numThreads, urls, resultPanel, statusLabel);
                    threadsController.startScraping();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid number of threads.",
                            "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }
}