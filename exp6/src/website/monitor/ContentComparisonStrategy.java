package website.monitor;

public interface ContentComparisonStrategy {
    boolean compare(String previousContent, String currentContent);
    String getStrategyName();
} 