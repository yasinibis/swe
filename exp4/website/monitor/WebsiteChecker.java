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
        System.out.println("Checking for updates on: " + url);
        // Simulate a random update detection
        boolean hasUpdate = Math.random() > 0.5;
        if (hasUpdate) {
            System.out.println("Update detected on: " + url);
        } else {
            System.out.println("No update detected on: " + url);
        }
        return hasUpdate;
    }

    private String fetchWebsiteContent(String urlString) throws Exception {
        // Simulate fetching website content
        return "Dummy content for " + urlString;
    }
} 