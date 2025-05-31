package website.monitor;

public class NotificationPreferences {
    public enum Frequency {
        DAILY, WEEKLY, MONTHLY
    }

    public enum Channel {
        EMAIL, SMS, PUSH_NOTIFICATION
    }

    private Frequency frequency;
    private Channel channel;
    private String contactInfo;

    public NotificationPreferences(Frequency frequency, Channel channel, String contactInfo) {
        this.frequency = frequency;
        this.channel = channel;
        this.contactInfo = contactInfo;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getContactInfo() {
        return contactInfo;
    }
} 