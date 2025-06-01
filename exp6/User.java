import java.util.ArrayList;
import java.util.List;

public class User implements Observer {
    private final String name;
    private final int checkFrequency;
    private final List<ResponseChannel> responseChannels;

    public User(String name, int checkFrequency, ResponseChannel initialChannel) {
        this.name = name;
        this.checkFrequency = checkFrequency;
        this.responseChannels = new ArrayList<>();
        if (initialChannel != null) {
            responseChannels.add(initialChannel);
        }
    }

    public void addResponseChannel(ResponseChannel channel) {
        if (channel != null) {
            responseChannels.add(channel);
        }
    }

    @Override
    public void update(String message) {
        for (ResponseChannel channel : responseChannels) {
            channel.sendNotification(name, message);
        }
    }
} 