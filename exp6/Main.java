package exp6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WebsiteComparator comparator = new WebsiteComparator();
        
        // Register observers
        ComparisonLogger logger = new ComparisonLogger();
        comparator.registerObserver(logger);
        
        // Add some example URLs
        comparator.addUrl("https://www.example.com");
        comparator.addUrl("https://www.example.org");
        comparator.addUrl("https://www.example.net");

        try {
            // Test with Content Size Strategy
            System.out.println("\nTesting Content Size Strategy:");
            comparator.setStrategy(new ContentSizeStrategy());
            comparator.compareAll();

            // Test with HTML Content Strategy
            System.out.println("\nTesting HTML Content Strategy:");
            comparator.setStrategy(new HtmlContentStrategy());
            comparator.compareAll();

            // Test with Text Content Strategy
            System.out.println("\nTesting Text Content Strategy:");
            comparator.setStrategy(new TextContentStrategy());
            comparator.compareAll();

        } catch (IOException e) {
            System.err.println("Error during comparison: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 