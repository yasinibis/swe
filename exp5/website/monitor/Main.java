package website.monitor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demonstration ===\n");

        // 1. Create the Subject (WebsiteMonitor)
        System.out.println("1. Creating WebsiteMonitor (Subject)");
        WebsiteMonitor monitor = new WebsiteMonitor("https://example.com", 5);
        System.out.println("   - Monitor created with URL: https://example.com");
        System.out.println("   - Check interval: 5 seconds\n");

        // 2. Create different Observers with different preferences
        System.out.println("2. Creating Notification Services (Observers)");
        
        // Email observer
        NotificationPreferences emailPrefs = new NotificationPreferences(
            NotificationPreferences.Channel.EMAIL,
            NotificationPreferences.Frequency.DAILY,
            "user@example.com"
        );
        NotificationService emailService = new NotificationService(emailPrefs);
        System.out.println("   - Created Email Notification Service");
        
        // SMS observer
        NotificationPreferences smsPrefs = new NotificationPreferences(
            NotificationPreferences.Channel.SMS,
            NotificationPreferences.Frequency.WEEKLY,
            "+1234567890"
        );
        NotificationService smsService = new NotificationService(smsPrefs);
        System.out.println("   - Created SMS Notification Service");
        
        // Console observer
        NotificationPreferences consolePrefs = new NotificationPreferences(
            NotificationPreferences.Channel.CONSOLE,
            NotificationPreferences.Frequency.MONTHLY,
            "console"
        );
        NotificationService consoleService = new NotificationService(consolePrefs);
        System.out.println("   - Created Console Notification Service\n");

        // 3. Register observers (demonstrating loose coupling)
        System.out.println("3. Registering Observers with Subject");
        monitor.registerObserver(emailService);
        System.out.println("   - Registered Email Service");
        monitor.registerObserver(smsService);
        System.out.println("   - Registered SMS Service");
        monitor.registerObserver(consoleService);
        System.out.println("   - Registered Console Service\n");

        // 4. Start monitoring (this will trigger notifications)
        System.out.println("4. Starting Website Monitoring");
        System.out.println("   - All registered observers will be notified of status changes");
        monitor.startMonitoring();

        // 5. Demonstrate dynamic observer management
        try {
            // Let it run for a while to see notifications
            Thread.sleep(10000);
            
            System.out.println("\n5. Demonstrating Dynamic Observer Management");
            System.out.println("   - Removing SMS Service");
            monitor.removeObserver(smsService);
            
            // Let it run a bit more to see the difference
            Thread.sleep(5000);
            
            System.out.println("\n6. Adding New Observer");
            NotificationPreferences newPrefs = new NotificationPreferences(
                NotificationPreferences.Channel.EMAIL,
                NotificationPreferences.Frequency.DAILY,
                "newuser@example.com"
            );
            NotificationService newService = new NotificationService(newPrefs);
            monitor.registerObserver(newService);
            System.out.println("   - Added new Email Service for newuser@example.com");
            
            // Let it run a bit more to see the new observer in action
            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 6. Cleanup
        System.out.println("\n7. Cleaning Up");
        monitor.stopMonitoring();
        System.out.println("   - Monitoring stopped");
        System.out.println("   - All observers unregistered");
    }
} 