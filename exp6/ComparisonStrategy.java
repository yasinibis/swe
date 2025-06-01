public interface ComparisonStrategy {
    boolean compare(String oldContent, String newContent);
    String getStrategyName();
} 