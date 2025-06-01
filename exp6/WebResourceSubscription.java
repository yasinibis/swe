import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WebResourceSubscription implements Subject {
    private final URI resourceUrl;
    private LocalDateTime lastCheckTime;
    private String cachedContent = "";
    private final List<Observer> notificationSubscribers = new ArrayList<>();
    private ComparisonStrategy comparisonStrategy;

    public WebResourceSubscription(URI resourceUrl, ComparisonStrategy strategy) {
        this.resourceUrl = resourceUrl;
        this.comparisonStrategy = strategy;
    }

    public void setComparisonStrategy(ComparisonStrategy strategy) {
        this.comparisonStrategy = strategy;
    }

    public URI getResourceUrl() {
        return resourceUrl;
    }

    @Override
    public void registerObserver(Observer observer) {
        notificationSubscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        notificationSubscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String notificationMessage = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + 
                        " - " + resourceUrl.toString() + " has changed! (Strategy: " + comparisonStrategy.getStrategyName() + ")";
        for (Observer subscriber : notificationSubscribers) {
            subscriber.update(notificationMessage);
        }
    }

    public void checkUpdate() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(resourceUrl)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String newContent = response.body();

            if (!comparisonStrategy.compare(cachedContent, newContent)) {
                cachedContent = newContent;
                notifyObservers();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking website: " + e.getMessage());
        }
    }
} 