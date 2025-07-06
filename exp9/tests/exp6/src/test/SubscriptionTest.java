import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

public class SubscriptionTest {
    
    private URI testUri;
    private ComparisonStrategy testStrategy;
    private Subscription subscription;
    
    @BeforeEach
    void setUp() {
        testUri = URI.create("https://www.google.com");
        testStrategy = new ContentSizeStrategy();
        subscription = new Subscription(testUri, testStrategy);
    }
    
    @Test
    @DisplayName("Subscription constructor with valid input should create subscription successfully")
    void testSubscriptionConstructorWithValidInput() {
        assertNotNull(subscription, "Subscription was created");
    }
    
    @Test
    @DisplayName("Subscription constructor with null URI should throw NullPointerException")
    void testSubscriptionConstructorWithNullUri() {
        assertThrows(NullPointerException.class, () -> {
            new Subscription(null, testStrategy);
        }, "Subscription with null URI throws exception");
    }
    
    @Test
    @DisplayName("Subscription constructor with null strategy should create subscription successfully")
    void testSubscriptionConstructorWithNullStrategy() {
        Subscription nullStrategySubscription = new Subscription(testUri, null);
        assertNotNull(nullStrategySubscription, "Subscription with null strategy was created");
    }
    
    @Test
    @DisplayName("getResourceUrl should return the correct URI")
    void testGetResourceUrl() {
        URI result = subscription.getResourceUrl();
        assertEquals(testUri, result, "getResourceUrl returns correct URI");
    }
    
    @Test
    @DisplayName("registerObserver with valid observer should not throw exception")
    void testRegisterObserverWithValidObserver() {
        Observer observer = new User("Yasin", 5, new MailChannel());
        assertDoesNotThrow(() -> {
            subscription.registerObserver(observer);
        }, "registerObserver with valid observer works");
    }
    
    @Test
    @DisplayName("registerObserver with null observer should not throw exception")
    void testRegisterObserverWithNullObserver() {
        assertDoesNotThrow(() -> {
            subscription.registerObserver(null);
        }, "registerObserver with null observer works");
    }
    
    @Test
    @DisplayName("removeObserver with valid observer should not throw exception")
    void testRemoveObserverWithValidObserver() {
        Observer observer = new User("Yasin", 5, new MailChannel());
        subscription.registerObserver(observer);
        
        assertDoesNotThrow(() -> {
            subscription.removeObserver(observer);
        }, "removeObserver with valid observer works");
    }
    
    @Test
    @DisplayName("removeObserver with null observer should not throw exception")
    void testRemoveObserverWithNullObserver() {
        assertDoesNotThrow(() -> {
            subscription.removeObserver(null);
        }, "removeObserver with null observer works");
    }
    
    @Test
    @DisplayName("notifyObservers with no observers should not throw exception")
    void testNotifyObserversWithNoObservers() {
        assertDoesNotThrow(() -> {
            subscription.notifyObservers();
        }, "notifyObservers with no observers works");
    }
    
    @Test
    @DisplayName("notifyObservers with observers should not throw exception")
    void testNotifyObserversWithObservers() {
        Observer observer = new User("Yasin", 5, new MailChannel());
        subscription.registerObserver(observer);
        
        assertDoesNotThrow(() -> {
            subscription.notifyObservers();
        }, "notifyObservers with observers works");
    }
    
    @Test
    @DisplayName("setComparisonStrategy should not throw exception")
    void testSetComparisonStrategy() {
        ComparisonStrategy newStrategy = new TextContentStrategy();
        assertDoesNotThrow(() -> {
            subscription.setComparisonStrategy(newStrategy);
        }, "setComparisonStrategy works");
    }
    
    @Test
    @DisplayName("checkUpdate should not throw exception")
    void testCheckUpdate() {
        assertDoesNotThrow(() -> {
            subscription.checkUpdate();
        }, "checkUpdate works");
    }
    
    @Test
    @DisplayName("Subscription should implement Subject interface")
    void testSubscriptionImplementsSubject() {
        assertTrue(subscription instanceof Subject, "Subscription implements Subject");
    }
} 