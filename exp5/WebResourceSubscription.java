import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WebResourceSubscription implements Subject {
    private final URI resourceUrl;
    private final List<Observer> notificationSubscribers = new ArrayList<>();

    public WebResourceSubscription(URI resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public URI getResourceUrl() {
        return resourceUrl;
    }


    public void addObserver(Observer observer) {
        notificationSubscribers.add(observer);
    }


    public void deleteObserver(Observer observer) {
        notificationSubscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String notificationMessage = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + " , " + resourceUrl.toString() + " changed!";
        for (Observer subscriber : notificationSubscribers) {
            subscriber.update(notificationMessage);
        }
    }

    public void checkUpdate() {
        System.out.println("checkUpdate called for: " + resourceUrl);
        notifyObservers();
    }
} 