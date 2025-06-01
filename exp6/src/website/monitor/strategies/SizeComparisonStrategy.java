package website.monitor.strategies;

import website.monitor.ContentComparisonStrategy;

public class SizeComparisonStrategy implements ContentComparisonStrategy {
    @Override
    public boolean compare(String previousContent, String currentContent) {
        if (previousContent == null || currentContent == null) {
            return false;
        }
        return previousContent.length() == currentContent.length();
    }

    @Override
    public String getStrategyName() {
        return "Size Comparison";
    }
} 