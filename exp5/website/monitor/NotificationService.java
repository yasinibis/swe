package website.monitor;

public class NotificationService implements Observer {
    private NotificationPreferences preferences;

    public NotificationService(NotificationPreferences preferences) {
        this.preferences = preferences;
    }

    public NotificationPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(NotificationPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void update(WebsiteStatus status) {
        if (shouldNotify(status)) {
            sendNotification(status);
        }
    }

    private boolean shouldNotify(WebsiteStatus status) {
        // Implement notification logic based on preferences
        return true; // Simplified for this example
    }

    private void sendNotification(WebsiteStatus status) {
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
        System.out.println("Sending email notification to " + preferences.getContactInfo() + ": " + status);
    }

    private void sendSMSNotification(WebsiteStatus status) {
        System.out.println("Sending SMS notification to " + preferences.getContactInfo() + ": " + status);
    }

    private void sendConsoleNotification(WebsiteStatus status) {
        System.out.println("Console notification: " + status);
    }
} 