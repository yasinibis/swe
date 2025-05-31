package exp6;

public class ComparisonLogger implements ComparisonObserver {
    @Override
    public void update(ComparisonResult result) {
        System.out.println("\n=== Comparison Results Log ===");
        System.out.println("Strategy: " + result.getStrategyName());
        System.out.println("Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("Results:");
        System.out.println(result);
        System.out.println("===========================\n");
    }
} 