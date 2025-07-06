package website.monitor;

public class NotificationService {
    public void sendNotification(Subscription subscription) {
        NotificationPreferences preferences = subscription.getPreferences();
        String message = "Update detected for website: " + subscription.getUrl();
        
        switch (preferences.getChannel()) {
            case EMAIL:
                sendEmail(preferences.getContactInfo(), message);
                break;
            case SMS:
                sendSMS(preferences.getContactInfo(), message);
                break;
            case PUSH_NOTIFICATION:
                sendPushNotification(preferences.getContactInfo(), message);
                break;
        }
    }

    private void sendEmail(String email, String message) {
        // Implementation would connect to an email service
        System.out.println("Sending email to " + email + ": " + message);
    }

    private void sendSMS(String phoneNumber, String message) {
        // Implementation would connect to an SMS service
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    private void sendPushNotification(String deviceId, String message) {
        // Implementation would connect to a push notification service
        System.out.println("Sending push notification to " + deviceId + ": " + message);
    }
} 