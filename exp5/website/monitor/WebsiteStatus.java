package website.monitor;

public class WebsiteStatus {
    private final String url;
    private final boolean isAvailable;
    private final long responseTime;
    private final String statusMessage;

    public WebsiteStatus(String url, boolean isAvailable, long responseTime, String statusMessage) {
        this.url = url;
        this.isAvailable = isAvailable;
        this.responseTime = responseTime;
        this.statusMessage = statusMessage;
    }

    public String getUrl() {
        return url;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public String toString() {
        return String.format("Website: %s, Available: %b, Response Time: %dms, Status: %s",
                url, isAvailable, responseTime, statusMessage);
    }
} 