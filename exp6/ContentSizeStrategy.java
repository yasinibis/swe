package exp6;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class ContentSizeStrategy implements ComparisonStrategy {
    @Override
    public boolean compare(String url1, String url2) throws IOException {
        long size1 = getContentSize(url1);
        long size2 = getContentSize(url2);
        return size1 == size2;
    }

    @Override
    public String getStrategyName() {
        return "Content Size Comparison";
    }

    private long getContentSize(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        
        long contentLength = connection.getContentLengthLong();
        connection.disconnect();
        
        return contentLength;
    }
} 