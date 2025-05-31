package exp6;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

public class TextContentStrategy implements ComparisonStrategy {
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");

    @Override
    public boolean compare(String url1, String url2) throws IOException {
        String text1 = getTextContent(url1);
        String text2 = getTextContent(url2);
        return text1.equals(text2);
    }

    @Override
    public String getStrategyName() {
        return "Text Content Comparison";
    }

    private String getTextContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        connection.disconnect();
        
        // Remove HTML tags and normalize whitespace
        String textContent = HTML_TAG_PATTERN.matcher(content.toString()).replaceAll("");
        return textContent.replaceAll("\\s+", " ").trim();
    }
} 