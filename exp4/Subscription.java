package website.monitor;

public class Subscription {
    private String url;
    private NotificationPreferences preferences;

    public Subscription(String url, NotificationPreferences preferences) {
        this.url = url;
        this.preferences = preferences;
    }

    public String getUrl() {
        return url;
    }

    public NotificationPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(NotificationPreferences preferences) {
        this.preferences = preferences;
    }
} 