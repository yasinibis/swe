import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    WebMonitorTest.class,
    UserTest.class,
    SubscriptionTest.class,
    ComparisonStrategyTest.class,
    ResponseChannelTest.class
})
@DisplayName("All Unit Tests for WebMonitor System")
public class AllTests {
} 