package website.monitor;

import java.util.HashSet;
import java.util.Set;

public class NotificationService implements Observer {
    private NotificationPreferences preferences;
    private final Set<String> subscribedWebsites;

    public NotificationService(NotificationPreferences preferences) {
        this.preferences = preferences;
        this.subscribedWebsites = new HashSet<>();
    }

    public void updatePreferences(NotificationPreferences preferences) {
        this.preferences = preferences;
    }

    public void subscribe(String url) {
        subscribedWebsites.add(url);
    }

    public void unsubscribe(String url) {
        subscribedWebsites.remove(url);
    }

    public boolean isSubscribed(String url) {
        return subscribedWebsites.contains(url);
    }

    @Override
    public void update(WebsiteStatus status) {
        if (!subscribedWebsites.contains(status.getUrl())) {
            return;
        }

        String message = String.format("Notification for %s:\n%s", status.getUrl(), status.toString());
        
        switch (preferences.getChannel()) {
            case EMAIL -> System.out.println("Sending email to " + preferences.getContactInfo() + ":\n" + message);
            case SMS -> System.out.println("Sending SMS to " + preferences.getContactInfo() + ":\n" + message);
            case CONSOLE -> System.out.println("Console notification:\n" + message);
        }
    }
} 