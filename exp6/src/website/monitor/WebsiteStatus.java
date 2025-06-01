package website.monitor;

public class WebsiteStatus {
    private final String url;
    private final boolean isAvailable;
    private final long responseTime;
    private final String statusMessage;
    private final boolean contentChanged;

    public WebsiteStatus(String url, boolean isAvailable, long responseTime, String statusMessage, boolean contentChanged) {
        this.url = url;
        this.isAvailable = isAvailable;
        this.responseTime = responseTime;
        this.statusMessage = statusMessage;
        this.contentChanged = contentChanged;
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

    public boolean isContentChanged() {
        return contentChanged;
    }

    @Override
    public String toString() {
        return String.format("Website: %s\nStatus: %s\nResponse Time: %dms\nContent Changed: %s",
                url, statusMessage, responseTime, contentChanged ? "Yes" : "No");
    }
} 