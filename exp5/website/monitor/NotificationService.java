package website.monitor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationService implements Observer {
    private NotificationPreferences preferences;
    private final Map<String, LocalDateTime> lastNotificationTimes;
    private final Map<String, Boolean> subscriptionStatus;

    public NotificationService(NotificationPreferences preferences) {
        this.preferences = preferences;
        this.lastNotificationTimes = new ConcurrentHashMap<>();
        this.subscriptionStatus = new ConcurrentHashMap<>();
    }

    public NotificationPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(NotificationPreferences preferences) {
        this.preferences = preferences;
    }

    public void subscribe(String url) {
        subscriptionStatus.put(url, true);
    }

    public void unsubscribe(String url) {
        subscriptionStatus.put(url, false);
    }

    public boolean isSubscribed(String url) {
        return subscriptionStatus.getOrDefault(url, false);
    }

    @Override
    public void update(WebsiteStatus status) {
        if (shouldNotify(status)) {
            sendNotification(status);
            lastNotificationTimes.put(status.getUrl(), LocalDateTime.now());
        }
    }

    private boolean shouldNotify(WebsiteStatus status) {
        if (!isSubscribed(status.getUrl())) {
            return false;
        }

        LocalDateTime lastNotification = lastNotificationTimes.get(status.getUrl());
        if (lastNotification == null) {
            return true;
        }

        long hoursSinceLastNotification = ChronoUnit.HOURS.between(lastNotification, LocalDateTime.now());
        
        switch (preferences.getFrequency()) {
            case DAILY:
                return hoursSinceLastNotification >= 24;
            case WEEKLY:
                return hoursSinceLastNotification >= 168; // 24 * 7
            case MONTHLY:
                return hoursSinceLastNotification >= 720; // 24 * 30
            default:
                return true;
        }
    }

    private void sendNotification(WebsiteStatus status) {
        if (!status.isContentChanged() && !status.isAvailable()) {
            return; // Only send notifications for content changes or availability issues
        }

        switch (preferences.getChannel()) {
            case EMAIL:
                sendEmailNotification(status);
                break;
            case SMS:
                sendSMSNotification(status);
                break;
            case CONSOLE:
                sendConsoleNotification(status);
                break;
        }
    }

    private void sendEmailNotification(WebsiteStatus status) {
        System.out.println("Sending email notification to " + preferences.getContactInfo() + ":\n" + status);
    }

    private void sendSMSNotification(WebsiteStatus status) {
        System.out.println("Sending SMS notification to " + preferences.getContactInfo() + ":\n" + status);
    }

    private void sendConsoleNotification(WebsiteStatus status) {
        System.out.println("Console notification:\n" + status);
    }
} 