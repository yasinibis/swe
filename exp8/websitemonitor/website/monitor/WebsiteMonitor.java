package website.monitor;

import java.util.ArrayList;
import java.util.List;

public class WebsiteMonitor {
    private List<Subscription> subscriptions;
    private NotificationService notificationService;
    private WebsiteChecker websiteChecker;

    public WebsiteMonitor() {
        this.subscriptions = new ArrayList<>();
        this.notificationService = new NotificationService();
        this.websiteChecker = new WebsiteChecker();
    }

    public void registerSubscription(String url, NotificationPreferences preferences) {
        Subscription subscription = new Subscription(url, preferences);
        subscriptions.add(subscription);
    }

    public void removeSubscription(String url) {
        subscriptions.removeIf(sub -> sub.getUrl().equals(url));
    }

    public void updateSubscription(String url, NotificationPreferences newPreferences) {
        for (Subscription sub : subscriptions) {
            if (sub.getUrl().equals(url)) {
                sub.setPreferences(newPreferences);
                break;
            }
        }
    }

    public void checkForUpdates() {
        for (Subscription subscription : subscriptions) {
            boolean hasUpdate = websiteChecker.checkForUpdates(subscription.getUrl());
            if (hasUpdate) {
                notificationService.sendNotification(subscription);
            }
        }
    }

    public static void main(String[] args) {
        WebsiteMonitor monitor = new WebsiteMonitor();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("\n--- Website Monitoring System ---");
            System.out.println("1. Register for website updates");
            System.out.println("2. List subscriptions");
            System.out.println("3. Modify a subscription");
            System.out.println("4. Remove a subscription");
            System.out.println("5. Check for updates");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter website URL: ");
                    String url = scanner.nextLine();
                    System.out.println("Choose notification frequency: 1. DAILY 2. WEEKLY 3. MONTHLY");
                    String freqInput = scanner.nextLine();
                    NotificationPreferences.Frequency freq = NotificationPreferences.Frequency.DAILY;
                    if (freqInput.equals("2")) freq = NotificationPreferences.Frequency.WEEKLY;
                    else if (freqInput.equals("3")) freq = NotificationPreferences.Frequency.MONTHLY;
                    System.out.println("Choose channel: 1. EMAIL 2. SMS 3. PUSH_NOTIFICATION");
                    String chanInput = scanner.nextLine();
                    NotificationPreferences.Channel channel = NotificationPreferences.Channel.EMAIL;
                    if (chanInput.equals("2")) channel = NotificationPreferences.Channel.SMS;
                    else if (chanInput.equals("3")) channel = NotificationPreferences.Channel.PUSH_NOTIFICATION;
                    System.out.print("Enter contact info (email/phone/device id): ");
                    String contact = scanner.nextLine();
                    NotificationPreferences prefs = new NotificationPreferences(freq, channel, contact);
                    monitor.registerSubscription(url, prefs);
                    System.out.println("Subscription registered.");
                    break;
                case "2":
                    if (monitor.subscriptions.isEmpty()) {
                        System.out.println("No subscriptions found.");
                    } else {
                        System.out.println("Current subscriptions:");
                        for (int i = 0; i < monitor.subscriptions.size(); i++) {
                            Subscription sub = monitor.subscriptions.get(i);
                            NotificationPreferences p = sub.getPreferences();
                            System.out.printf("%d. %s | %s | %s | %s\n", i+1, sub.getUrl(), p.getFrequency(), p.getChannel(), p.getContactInfo());
                        }
                    }
                    break;
                case "3":
                    if (monitor.subscriptions.isEmpty()) {
                        System.out.println("No subscriptions to modify.");
                        break;
                    }
                    System.out.print("Enter the URL of the subscription to modify: ");
                    String modUrl = scanner.nextLine();
                    boolean found = false;
                    for (Subscription sub : monitor.subscriptions) {
                        if (sub.getUrl().equals(modUrl)) {
                            System.out.println("Choose new frequency: 1. DAILY 2. WEEKLY 3. MONTHLY");
                            String newFreqInput = scanner.nextLine();
                            NotificationPreferences.Frequency newFreq = NotificationPreferences.Frequency.DAILY;
                            if (newFreqInput.equals("2")) newFreq = NotificationPreferences.Frequency.WEEKLY;
                            else if (newFreqInput.equals("3")) newFreq = NotificationPreferences.Frequency.MONTHLY;
                            System.out.println("Choose new channel: 1. EMAIL 2. SMS 3. PUSH_NOTIFICATION");
                            String newChanInput = scanner.nextLine();
                            NotificationPreferences.Channel newChannel = NotificationPreferences.Channel.EMAIL;
                            if (newChanInput.equals("2")) newChannel = NotificationPreferences.Channel.SMS;
                            else if (newChanInput.equals("3")) newChannel = NotificationPreferences.Channel.PUSH_NOTIFICATION;
                            System.out.print("Enter new contact info: ");
                            String newContact = scanner.nextLine();
                            NotificationPreferences newPrefs = new NotificationPreferences(newFreq, newChannel, newContact);
                            sub.setPreferences(newPrefs);
                            System.out.println("Subscription updated.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Subscription not found.");
                    break;
                case "4":
                    if (monitor.subscriptions.isEmpty()) {
                        System.out.println("No subscriptions to remove.");
                        break;
                    }
                    System.out.print("Enter the URL of the subscription to remove: ");
                    String remUrl = scanner.nextLine();
                    int before = monitor.subscriptions.size();
                    monitor.removeSubscription(remUrl);
                    if (monitor.subscriptions.size() < before) {
                        System.out.println("Subscription removed.");
                    } else {
                        System.out.println("Subscription not found.");
                    }
                    break;
                case "5":
                    monitor.checkForUpdates();
                    System.out.println("Update check complete.");
                    break;
                case "6":
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
} 