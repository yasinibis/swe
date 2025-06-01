import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WebResourceSubscription implements Subject {
    private final URI resourceUrl;
    private LocalDateTime lastCheckTime;
    private String cachedContent = "";
    private final List<Observer> notificationSubscribers = new ArrayList<>();

    public WebResourceSubscription(URI resourceUrl) {
        this.resourceUrl = resourceUrl;
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
                        " - " + resourceUrl.toString() + " has changed!";
        for (Observer subscriber : notificationSubscribers) {
            subscriber.update(notificationMessage);
        }
    }

    public void checkUpdate() {
        System.out.println("Mock checkUpdate called for: " + resourceUrl);
        notifyObservers();
    }
} 