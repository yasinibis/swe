package website.monitor.strategies;

import website.monitor.ContentComparisonStrategy;
import java.security.MessageDigest;
import java.util.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextComparisonStrategy implements ContentComparisonStrategy {
    @Override
    public boolean compare(String previousContent, String currentContent) {
        if (previousContent == null || currentContent == null) {
            return false;
        }
        try {
            Document previousDoc = Jsoup.parse(previousContent);
            Document currentDoc = Jsoup.parse(currentContent);
            
            String previousText = previousDoc.text();
            String currentText = currentDoc.text();
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] previousHash = digest.digest(previousText.getBytes("UTF-8"));
            digest.reset();
            byte[] currentHash = digest.digest(currentText.getBytes("UTF-8"));
            
            return Base64.getEncoder().encodeToString(previousHash)
                    .equals(Base64.getEncoder().encodeToString(currentHash));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getStrategyName() {
        return "Text Content Comparison";
    }
} 