public class MailChannel implements ResponseChannel {
    @Override
    public void send(String message) {
        System.out.println("Mail sent: " + message);
    }
} 