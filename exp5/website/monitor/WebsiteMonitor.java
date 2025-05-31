package website.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebsiteMonitor implements Subject {
    private final List<Observer> observers;
    private final String url;
    private final int checkInterval;
    private final ScheduledExecutorService scheduler;
    private WebsiteStatus lastStatus;
    private final Random random;

    public WebsiteMonitor() {
        this("https://www.example.com", 60);
    }

    public WebsiteMonitor(String url, int checkInterval) {
        this.url = url;
        this.checkInterval = checkInterval;
        this.observers = new ArrayList<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.random = new Random();
    }

    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(this::checkWebsite, 0, checkInterval, TimeUnit.SECONDS);
    }

    public void stopMonitoring() {
        scheduler.shutdown();
    }

    public void checkWebsite() {
        try {
            // Simulate network delay
            Thread.sleep(random.nextInt(1000) + 500);

            // Randomly determine if website is up (80% chance of being up)
            boolean isAvailable = random.nextDouble() < 0.8;
            
            // Simulate response time between 100ms and 2000ms
            long responseTime = random.nextInt(1900) + 100;

            String statusMessage;
            if (isAvailable) {
                // Simulate different successful scenarios
                int scenario = random.nextInt(3);
                switch (scenario) {
                    case 0:
                        statusMessage = "Website is up and responding normally";
                        break;
                    case 1:
                        statusMessage = "Website is up but responding slowly";
                        break;
                    default:
                        statusMessage = "Website is up with optimal performance";
                }
            } else {
                // Simulate different failure scenarios
                int scenario = random.nextInt(3);
                switch (scenario) {
                    case 0:
                        statusMessage = "Website is down - Connection timeout";
                        break;
                    case 1:
                        statusMessage = "Website is down - Server error";
                        break;
                    default:
                        statusMessage = "Website is down - DNS resolution failed";
                }
            }

            lastStatus = new WebsiteStatus(url, isAvailable, responseTime, statusMessage);
            notifyObservers(lastStatus);

        } catch (InterruptedException e) {
            lastStatus = new WebsiteStatus(url, false, 0, "Error: Mockup check interrupted");
            notifyObservers(lastStatus);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            if (lastStatus != null) {
                observer.update(lastStatus);
            }
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(WebsiteStatus status) {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return new ArrayList<>(observers);
    }
} 