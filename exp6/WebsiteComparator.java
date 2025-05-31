package exp6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteComparator implements Subject {
    private ComparisonStrategy strategy;
    private final List<String> urls;
    private final List<ComparisonObserver> observers;

    public WebsiteComparator() {
        this.urls = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void setStrategy(ComparisonStrategy strategy) {
        this.strategy = strategy;
    }

    public void addUrl(String url) {
        urls.add(url);
    }

    public void clearUrls() {
        urls.clear();
    }

    public ComparisonResult compareAll() throws IOException {
        if (strategy == null) {
            throw new IllegalStateException("Comparison strategy not set");
        }
        if (urls.size() < 2) {
            throw new IllegalStateException("At least two URLs are required for comparison");
        }

        ComparisonResult result = new ComparisonResult(strategy.getStrategyName());
        
        for (int i = 0; i < urls.size(); i++) {
            for (int j = i + 1; j < urls.size(); j++) {
                String url1 = urls.get(i);
                String url2 = urls.get(j);
                boolean areIdentical = strategy.compare(url1, url2);
                result.addComparison(url1, url2, areIdentical);
            }
        }
        
        // Notify all observers about the comparison result
        notifyObservers(result);
        
        return result;
    }

    @Override
    public void registerObserver(ComparisonObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ComparisonObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(ComparisonResult result) {
        for (ComparisonObserver observer : observers) {
            observer.update(result);
        }
    }

    @Override
    public List<ComparisonObserver> getObservers() {
        return new ArrayList<>(observers);
    }
} 