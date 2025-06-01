package website.monitor.strategies;

import website.monitor.ContentComparisonStrategy;
import java.security.MessageDigest;
import java.util.Base64;

public class HtmlComparisonStrategy implements ContentComparisonStrategy {
    @Override
    public boolean compare(String previousContent, String currentContent) {
        if (previousContent == null || currentContent == null) {
            return false;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] previousHash = digest.digest(previousContent.getBytes("UTF-8"));
            digest.reset();
            byte[] currentHash = digest.digest(currentContent.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(previousHash)
                    .equals(Base64.getEncoder().encodeToString(currentHash));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getStrategyName() {
        return "HTML Content Comparison";
    }
} 