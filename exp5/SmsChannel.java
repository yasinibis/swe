public class SmsChannel implements ResponseChannel {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
} 