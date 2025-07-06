import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class WebResourceTracker {
    private final HashMap<String, User> registeredUsers = new HashMap<>();
    private final HashMap<String, WebResourceSubscription> activeSubscriptions = new HashMap<>();
    
    private WebResourceTracker() {}
    
    public void initializeTracking() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::WebsiteCheck, Settings.MONITOR_INTERVAL, Settings.TIME_UNIT);
    }

    public WebResourceTracker addNewUser(String name, int frequency, URI website, ResponseChannel channel) {
        User user = new User(name, frequency, channel);
        registeredUsers.put(name, user);
        WebResourceSubscription subscription = createSubscription(website);
        subscription.addObserver(user);
        
        return this;
    }

    public WebResourceTracker removeUser(String name) {
        User user = registeredUsers.remove(name);
        activeSubscriptions.values().forEach(subscription -> subscription.deleteObserver(user));
        return this;
    }

    private void WebsiteCheck() {
        activeSubscriptions.values().forEach(WebResourceSubscription::checkUpdate);
    }

    public WebResourceTracker createSubscriptionUser(String name, URI website) {
        User user = registeredUsers.get(name);
        WebResourceSubscription subscription = createSubscription(website);
        subscription.addObserver(user);
        return this;
    }
    
    public WebResourceTracker addNotificationChannel(String name, ResponseChannel channel) {
        User user = registeredUsers.get(name);
        user.addResponseChannel(channel);
        return this;
    }

    private WebResourceSubscription createSubscription(URI website) {
        return new WebResourceSubscription(website);
    }

    private WebResourceSubscription getSubscription(URI website) {
        return activeSubscriptions.computeIfAbsent(website.toString(), k -> new WebResourceSubscription(website));
    }
    ""
    
    public static void main(String[] args) {
        WebResourceTracker tracker = new WebResourceTracker();
        tracker.addNewUser("Yasin Ibis", 2, URI.create("www.google.com"), new MailChannel()).createSubscriptionUser("Yasin Ibis", URI.create("www.google.com"));
        tracker.initializeTracking();
    }
} 