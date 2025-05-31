package website.monitor;

public class NotificationPreferences {
    public enum Channel {
        EMAIL,
        SMS,
        CONSOLE
    }

    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY
    }

    private final Channel channel;
    private final Frequency frequency;
    private final String contactInfo;

    public NotificationPreferences(Channel channel, Frequency frequency) {
        this.channel = channel;
        this.frequency = frequency;
        this.contactInfo = "";
    }

    public NotificationPreferences(Channel channel, Frequency frequency, String contactInfo) {
        this.channel = channel;
        this.frequency = frequency;
        this.contactInfo = contactInfo;
    }

    public Channel getChannel() {
        return channel;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public String getContactInfo() {
        return contactInfo;
    }
} 