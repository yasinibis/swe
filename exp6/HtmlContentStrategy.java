package exp6;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class HtmlContentStrategy implements ComparisonStrategy {
    @Override
    public boolean compare(String url1, String url2) throws IOException {
        String content1 = getHtmlContent(url1);
        String content2 = getHtmlContent(url2);
        return content1.equals(content2);
    }

    @Override
    public String getStrategyName() {
        return "HTML Content Comparison";
    }

    private String getHtmlContent(String urlString) throws IOException {
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
        
        return content.toString();
    }
} 