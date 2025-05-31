package website.monitor;

import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(WebsiteStatus status);
    List<Observer> getObservers();
} 