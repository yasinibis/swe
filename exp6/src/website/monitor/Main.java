package website.monitor;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;
import website.monitor.strategies.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, WebsiteMonitor> monitors = new HashMap<>();
        
        // Create notification preferences
        System.out.println("=== Website Monitor Setup ===");
        System.out.println("Select notification channel:");
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. Console");
        int channelChoice = scanner.nextInt();
        
        System.out.println("\nSelect notification frequency:");
        System.out.println("1. Daily");
        System.out.println("2. Weekly");
        System.out.println("3. Monthly");
        int frequencyChoice = scanner.nextInt();
        
        scanner.nextLine(); // Consume newline
        
        System.out.println("\nEnter contact information (email or phone number):");
        String contactInfo = scanner.nextLine();
        
        // Create notification preferences
        NotificationPreferences.Channel channel = switch (channelChoice) {
            case 1 -> NotificationPreferences.Channel.EMAIL;
            case 2 -> NotificationPreferences.Channel.SMS;
            default -> NotificationPreferences.Channel.CONSOLE;
        };
        
        NotificationPreferences.Frequency frequency = switch (frequencyChoice) {
            case 1 -> NotificationPreferences.Frequency.DAILY;
            case 2 -> NotificationPreferences.Frequency.WEEKLY;
            default -> NotificationPreferences.Frequency.MONTHLY;
        };
        
        NotificationPreferences preferences = new NotificationPreferences(channel, frequency, contactInfo);
        NotificationService notificationService = new NotificationService(preferences);
        
        // Interactive menu
        while (true) {
            System.out.println("\n=== Website Monitor Menu ===");
            System.out.println("1. Change notification preferences");
            System.out.println("2. Register and monitor a new website");
            System.out.println("3. Unsubscribe from a website");
            System.out.println("4. Check subscription status");
            System.out.println("5. Change comparison strategy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1 -> {
                    System.out.println("\nSelect new notification channel:");
                    System.out.println("1. Email");
                    System.out.println("2. SMS");
                    System.out.println("3. Console");
                    int newChannel = scanner.nextInt();
                    
                    System.out.println("\nSelect new notification frequency:");
                    System.out.println("1. Daily");
                    System.out.println("2. Weekly");
                    System.out.println("3. Monthly");
                    int newFrequency = scanner.nextInt();
                    
                    scanner.nextLine(); // Consume newline
                    
                    System.out.println("\nEnter new contact information:");
                    String newContact = scanner.nextLine();
                    
                    NotificationPreferences.Channel channel2 = switch (newChannel) {
                        case 1 -> NotificationPreferences.Channel.EMAIL;
                        case 2 -> NotificationPreferences.Channel.SMS;
                        default -> NotificationPreferences.Channel.CONSOLE;
                    };
                    
                    NotificationPreferences.Frequency frequency2 = switch (newFrequency) {
                        case 1 -> NotificationPreferences.Frequency.DAILY;
                        case 2 -> NotificationPreferences.Frequency.WEEKLY;
                        default -> NotificationPreferences.Frequency.MONTHLY;
                    };
                    
                    preferences = new NotificationPreferences(channel2, frequency2, newContact);
                    notificationService.updatePreferences(preferences);
                }
                
                case 2 -> {
                    System.out.println("\nEnter website URL to monitor:");
                    String url = scanner.nextLine();
                    
                    System.out.println("Select comparison strategy:");
                    System.out.println("1. Size Comparison");
                    System.out.println("2. HTML Content Comparison");
                    System.out.println("3. Text Content Comparison");
                    int strategyChoice = scanner.nextInt();
                    
                    ContentComparisonStrategy strategy = switch (strategyChoice) {
                        case 1 -> new SizeComparisonStrategy();
                        case 2 -> new HtmlComparisonStrategy();
                        case 3 -> new TextComparisonStrategy();
                        default -> new HtmlComparisonStrategy();
                    };
                    
                    WebsiteMonitor monitor = new WebsiteMonitor(url, 30, strategy);
                    monitor.registerObserver(notificationService);
                    monitors.put(url, monitor);
                    notificationService.subscribe(url);
                    monitor.startMonitoring();
                    System.out.println("Started monitoring " + url);
                }
                
                case 3 -> {
                    System.out.println("\nEnter website URL to unsubscribe:");
                    String url = scanner.nextLine();
                    WebsiteMonitor monitor = monitors.get(url);
                    if (monitor != null) {
                        monitor.stopMonitoring();
                        monitors.remove(url);
                        notificationService.unsubscribe(url);
                        System.out.println("Unsubscribed from " + url);
                    } else {
                        System.out.println("Website not found in monitors");
                    }
                }
                
                case 4 -> {
                    System.out.println("\nEnter website URL to check status:");
                    String url = scanner.nextLine();
                    boolean isSubscribed = notificationService.isSubscribed(url);
                    System.out.println("Subscription status for " + url + ": " + (isSubscribed ? "Subscribed" : "Not subscribed"));
                }
                
                case 5 -> {
                    System.out.println("\nEnter website URL to change strategy:");
                    String url = scanner.nextLine();
                    WebsiteMonitor monitor = monitors.get(url);
                    if (monitor != null) {
                        System.out.println("Select comparison strategy:");
                        System.out.println("1. Size Comparison");
                        System.out.println("2. HTML Content Comparison");
                        System.out.println("3. Text Content Comparison");
                        int strategyChoice = scanner.nextInt();
                        
                        ContentComparisonStrategy strategy = switch (strategyChoice) {
                            case 1 -> new SizeComparisonStrategy();
                            case 2 -> new HtmlComparisonStrategy();
                            case 3 -> new TextComparisonStrategy();
                            default -> new HtmlComparisonStrategy();
                        };
                        
                        monitor.setComparisonStrategy(strategy);
                        System.out.println("Strategy updated for " + url);
                    } else {
                        System.out.println("Website not found in monitors");
                    }
                }
                
                case 6 -> {
                    System.out.println("\nStopping website monitoring...");
                    for (WebsiteMonitor monitor : monitors.values()) {
                        monitor.stopMonitoring();
                    }
                    scanner.close();
                    return;
                }
                
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
} 