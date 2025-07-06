package website.monitor;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <website_url>");
            System.out.println("Example: java Main https://example.com");
            System.exit(1);
        }

        String websiteUrl = args[0];
        System.out.println("Starting Website Monitor for: " + websiteUrl);

        NotificationPreferences preferences = new NotificationPreferences(
            NotificationPreferences.Frequency.DAILY,
            NotificationPreferences.Channel.EMAIL,
            "console@example.com"
        );

        // Create and configure the website monitor
        WebsiteMonitor monitor = new WebsiteMonitor();
        monitor.registerSubscription(websiteUrl, preferences);

        // Check for updates
        System.out.println("Checking for updates...");
        monitor.checkForUpdates();
        
        System.out.println("Website monitoring completed.");
    }
} 