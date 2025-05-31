package website.monitor;

import java.util.ArrayList;
import java.util.List;

public class WebsiteMonitor {
    private List<Subscription> subscriptions;
    private NotificationService notificationService;
    private WebsiteChecker websiteChecker;

    public WebsiteMonitor() {
        this.subscriptions = new ArrayList<>();
        this.notificationService = new NotificationService();
        this.websiteChecker = new WebsiteChecker();
    }

    public void registerSubscription(String url, NotificationPreferences preferences) {
        Subscription subscription = new Subscription(url, preferences);
        subscriptions.add(subscription);
    }

    public void removeSubscription(String url) {
        subscriptions.removeIf(sub -> sub.getUrl().equals(url));
    }

    public void updateSubscription(String url, NotificationPreferences newPreferences) {
        for (Subscription sub : subscriptions) {
            if (sub.getUrl().equals(url)) {
                sub.setPreferences(newPreferences);
                break;
            }
        }
    }

    public void checkForUpdates() {
        for (Subscription subscription : subscriptions) {
            boolean hasUpdate = websiteChecker.checkForUpdates(subscription.getUrl());
            if (hasUpdate) {
                notificationService.sendNotification(subscription);
            }
        }
    }
} 