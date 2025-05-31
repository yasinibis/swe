package exp6;

import java.util.List;

public interface Subject {
    void registerObserver(ComparisonObserver observer);
    void removeObserver(ComparisonObserver observer);
    void notifyObservers(ComparisonResult result);
    List<ComparisonObserver> getObservers();
} 