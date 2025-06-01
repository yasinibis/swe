public class SmsChannel implements ResponseChannel {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
} 