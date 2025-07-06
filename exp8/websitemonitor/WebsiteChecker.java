package website.monitor;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WebsiteChecker {
    private Map<String, String> lastContent;

    public WebsiteChecker() {
        this.lastContent = new HashMap<>();
    }

    public boolean checkForUpdates(String url) {
        try {
            String currentContent = fetchWebsiteContent(url);
            String previousContent = lastContent.get(url);

            if (previousContent == null) {
                lastContent.put(url, currentContent);
                return false;
            }

            boolean hasUpdate = !currentContent.equals(previousContent);
            if (hasUpdate) {
                lastContent.put(url, currentContent);
            }
            return hasUpdate;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String fetchWebsiteContent(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
} 