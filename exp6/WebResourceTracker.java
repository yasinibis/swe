import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebResourceTracker {
    private final HashMap<String, User> registeredUsers = new HashMap<>();
    private final HashMap<String, WebResourceSubscription> activeSubscriptions = new HashMap<>();
    private ComparisonStrategy defaultStrategy;
    
    private WebResourceTracker() {
        this.defaultStrategy = new ContentSizeStrategy(); // Default strategy
    }
    
    public void setDefaultStrategy(ComparisonStrategy strategy) {
        this.defaultStrategy = strategy;
    }
    
    public void initializeTracking() {
        System.out.println("WebResourceTracker initialized!");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::performResourceCheck, 0, Settings.MONITOR_INTERVAL, Settings.TIME_UNIT);
    }

    public WebResourceTracker addNewUser(String name, int frequency, URI website, ResponseChannel channel) {
        User user = new User(name, frequency, channel);
        registeredUsers.put(name, user);
        
        WebResourceSubscription subscription = createOrRetrieveSubscription(website);
        subscription.registerObserver(user);
        
        return this;
    }

    public WebResourceTracker removeUser(String name) {
        User user = registeredUsers.remove(name);
        if (user != null) {
            activeSubscriptions.values().forEach(subscription -> subscription.removeObserver(user));
        }
        return this;
    }

    public WebResourceTracker attachWebsiteToUser(String name, URI website) {
        User user = registeredUsers.get(name);
        if (user != null && website != null) {
            WebResourceSubscription subscription = createOrRetrieveSubscription(website);
            subscription.registerObserver(user);
        }
        return this;
    }
    
    public WebResourceTracker attachNotificationChannel(String name, ResponseChannel channel) {
        User user = registeredUsers.get(name);
        if (user != null && channel != null) {
            user.addResponseChannel(channel);
        }
        return this;
    }

    public WebResourceTracker setWebsiteStrategy(URI website, ComparisonStrategy strategy) {
        WebResourceSubscription subscription = activeSubscriptions.get(website.toString());
        if (subscription != null) {
            subscription.setComparisonStrategy(strategy);
        }
        return this;
    }

    private WebResourceSubscription createOrRetrieveSubscription(URI website) {
        return activeSubscriptions.computeIfAbsent(website.toString(), 
            k -> new WebResourceSubscription(website, defaultStrategy));
    }

    private void performResourceCheck() {
        System.out.println("Performing resource check ...");
        activeSubscriptions.values().forEach(WebResourceSubscription::checkUpdate);
    }
    
    public static void main(String[] args) {
        WebResourceTracker tracker = new WebResourceTracker();
        
        // Example usage with different strategies
        tracker.setDefaultStrategy(new ContentSizeStrategy());
        
        // Add a user with default strategy
        tracker.addNewUser("User1", 2, URI.create("https://www.google.com"), new MailChannel());
        
        // Add another user with a different strategy
        tracker.addNewUser("User2", 2, URI.create("https://www.github.com"), new SmsChannel());
        tracker.setWebsiteStrategy(URI.create("https://www.github.com"), new TextContentStrategy());
        
        tracker.initializeTracking();
    }
} 