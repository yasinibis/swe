package website.monitor;

import java.util.*;
import java.util.concurrent.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import website.monitor.strategies.*;

public class WebsiteMonitor implements Subject {
    private final List<Observer> observers;
    private final String url;
    private final int checkInterval;
    private final ScheduledExecutorService scheduler;
    private WebsiteStatus lastStatus;
    private String lastContent;
    private final Map<String, String> contentCache;
    private final Random random;
    private ContentComparisonStrategy comparisonStrategy;

    public WebsiteMonitor() {
        this("https://www.example.com", 60, new HtmlComparisonStrategy());
    }

    public WebsiteMonitor(String url, int checkInterval) {
        this(url, checkInterval, new HtmlComparisonStrategy());
    }

    public WebsiteMonitor(String url, int checkInterval, ContentComparisonStrategy strategy) {
        this.url = url;
        this.checkInterval = checkInterval;
        this.observers = new ArrayList<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.random = new Random();
        this.contentCache = new ConcurrentHashMap<>();
        this.comparisonStrategy = strategy;
    }

    public String getUrl() {
        return url;
    }

    public void setComparisonStrategy(ContentComparisonStrategy strategy) {
        this.comparisonStrategy = strategy;
    }

    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(this::checkWebsite, 0, checkInterval, TimeUnit.SECONDS);
    }

    public void stopMonitoring() {
        scheduler.shutdown();
    }

    private String fetchWebsiteContent() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                return content.toString();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void checkWebsite() {
        try {
            String content = fetchWebsiteContent();
            boolean contentChanged = false;

            if (content != null) {
                String previousContent = contentCache.get(url);
                contentChanged = !comparisonStrategy.compare(previousContent, content);
                contentCache.put(url, content);
            }

            boolean isAvailable = content != null;
            long responseTime = random.nextInt(1900) + 100;

            String statusMessage;
            if (isAvailable) {
                if (contentChanged) {
                    statusMessage = "Website content has been updated (" + comparisonStrategy.getStrategyName() + ")";
                } else {
                    statusMessage = "Website is up and content unchanged (" + comparisonStrategy.getStrategyName() + ")";
                }
            } else {
                statusMessage = "Website is down - Unable to fetch content";
            }

            lastStatus = new WebsiteStatus(url, isAvailable, responseTime, statusMessage, contentChanged);
            notifyObservers(lastStatus);

        } catch (Exception e) {
            lastStatus = new WebsiteStatus(url, false, 0, "Error: " + e.getMessage(), false);
            notifyObservers(lastStatus);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(WebsiteStatus status) {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
} 