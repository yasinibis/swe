package exp6;

import java.util.ArrayList;
import java.util.List;

public class ComparisonResult {
    private final String strategyName;
    private final List<Comparison> comparisons;

    public ComparisonResult(String strategyName) {
        this.strategyName = strategyName;
        this.comparisons = new ArrayList<>();
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void addComparison(String url1, String url2, boolean areIdentical) {
        comparisons.add(new Comparison(url1, url2, areIdentical));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Comparison Results using ").append(strategyName).append("\n");
        result.append("==========================================\n");
        
        for (Comparison comparison : comparisons) {
            result.append(String.format("Comparing:\n  %s\n  %s\nResult: %s\n\n",
                    comparison.url1,
                    comparison.url2,
                    comparison.areIdentical ? "IDENTICAL" : "DIFFERENT"));
        }
        
        return result.toString();
    }

    private static class Comparison {
        final String url1;
        final String url2;
        final boolean areIdentical;

        Comparison(String url1, String url2, boolean areIdentical) {
            this.url1 = url1;
            this.url2 = url2;
            this.areIdentical = areIdentical;
        }
    }
} 