package exp6;

import java.io.IOException;

public interface ComparisonStrategy {
    boolean compare(String url1, String url2) throws IOException;
    String getStrategyName();
} 