import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements Observer {
    private final String name;
    private LocalDateTime lastNotification;
    private final List<ResponseChannel> responseChannels = new ArrayList<>();

    public User(String name, int frequency, ResponseChannel responseChannel) {
        this.name = name;
        this.frequency = frequency;
        if (responseChannel != null) {addResponseChannel(responseChannel);}
    }

    public void addResponseChannel(ResponseChannel responseChannel) {
        this.responseChannels.add(responseChannel);
    }
    
    public void removeResponseChannel(ResponseChannel responseChannel) {
        this.responseChannels.remove(responseChannel);
    }

    @Override
    public void update(String message) {
        if (lastNotification == null) {
            responseChannels.forEach(channel -> channel.send("Message"));
            lastNotification = LocalDateTime.now();
        }
    }

    public String getName() {
        return name;
    }
} 