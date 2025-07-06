import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

public class WebMonitorTest {
    
    private WebMonitor webMonitor;
    private URI testWebsite;
    private ResponseChannel testChannel;
    
    @BeforeEach
    void setUp() {
        webMonitor = new WebMonitor();
        testWebsite = URI.create("https://www.google.com");
        testChannel = new MailChannel();
    }
    
    @Test
    @DisplayName("WebMonitor constructor should create a valid instance")
    void testWebMonitorConstructor() {
        assertNotNull(webMonitor, "WebMonitor was created");
    }
    
    @Test
    @DisplayName("addNewUser with valid parameters should return WebMonitor instance")
    void testAddNewUserWithValidInput() {
        WebMonitor result = webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        assertNotNull(result, "addNewUser returns WebMonitor");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("addNewUser with null name should throw NullPointerException")
    void testAddNewUserWithNullName() {
        assertThrows(NullPointerException.class, () -> {
            webMonitor.addNewUser(null, 5, testWebsite, testChannel);
        }, "addNewUser with null name throws exception");
    }
    
    @Test
    @DisplayName("addNewUser with empty name should work")
    void testAddNewUserWithEmptyName() {
        WebMonitor result = webMonitor.addNewUser("", 5, testWebsite, testChannel);
        assertNotNull(result, "addNewUser with empty name works");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("addNewUser with zero frequency should work")
    void testAddNewUserWithZeroFrequency() {
        WebMonitor result = webMonitor.addNewUser("Yasin", 0, testWebsite, testChannel);
        assertNotNull(result, "addNewUser with zero frequency works");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("addNewUser with negative frequency should work")
    void testAddNewUserWithNegativeFrequency() {
        WebMonitor result = webMonitor.addNewUser("Yasin", -1, testWebsite, testChannel);
        assertNotNull(result, "addNewUser with negative frequency works");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("addNewUser with null website should throw NullPointerException")
    void testAddNewUserWithNullWebsite() {
        assertThrows(NullPointerException.class, () -> {
            webMonitor.addNewUser("Yasin", 5, null, testChannel);
        }, "addNewUser with null website throws exception");
    }
    
    @Test
    @DisplayName("addNewUser with null channel should work")
    void testAddNewUserWithNullChannel() {
        WebMonitor result = webMonitor.addNewUser("Yasin", 5, testWebsite, null);
        assertNotNull(result, "addNewUser with null channel works");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("removeUser should return WebMonitor instance")
    void testRemoveUser() {
        // First add a user
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        // Then remove the user
        WebMonitor result = webMonitor.removeUser("Yasin");
        assertNotNull(result, "removeUser returns WebMonitor");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("removeUser with non-existent user should return WebMonitor instance")
    void testRemoveUserWithNonExistentUser() {
        WebMonitor result = webMonitor.removeUser("NonExistent");
        assertNotNull(result, "removeUser with non-existent user returns WebMonitor");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("removeUser with null name should throw NullPointerException")
    void testRemoveUserWithNullName() {
        assertThrows(NullPointerException.class, () -> {
            webMonitor.removeUser(null);
        }, "removeUser with null name throws exception");
    }
    
    @Test
    @DisplayName("removeUser with empty name should work")
    void testRemoveUserWithEmptyName() {
        WebMonitor result = webMonitor.removeUser("");
        assertNotNull(result, "removeUser with empty name works");
        assertSame(webMonitor, result, "Returns same WebMonitor");
    }
    
    @Test
    @DisplayName("setDefaultStrategy should accept valid strategy")
    void testSetDefaultStrategy() {
        ComparisonStrategy strategy = new ContentSizeStrategy();
        assertDoesNotThrow(() -> {
            webMonitor.setDefaultStrategy(strategy);
        }, "setDefaultStrategy works");
    }
    
    @Test
    @DisplayName("setDefaultStrategy with null should work")
    void testSetDefaultStrategyWithNull() {
        assertDoesNotThrow(() -> {
            webMonitor.setDefaultStrategy(null);
        }, "setDefaultStrategy with null works");
    }
    
    @Test
    @DisplayName("attachWebsiteToUser with valid parameters should not throw exception")
    void testAttachWebsiteToUser() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        URI newWebsite = URI.create("https://www.github.com");
        assertDoesNotThrow(() -> {
            webMonitor.attachWebsiteToUser("Yasin", newWebsite);
        }, "attachWebsiteToUser works");
    }
    
    @Test
    @DisplayName("attachWebsiteToUser with null name should work")
    void testAttachWebsiteToUserWithNullName() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        URI newWebsite = URI.create("https://www.github.com");
        assertDoesNotThrow(() -> {
            webMonitor.attachWebsiteToUser(null, newWebsite);
        }, "attachWebsiteToUser with null name works");
    }
    
    @Test
    @DisplayName("attachWebsiteToUser with null website should work")
    void testAttachWebsiteToUserWithNullWebsite() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        assertDoesNotThrow(() -> {
            webMonitor.attachWebsiteToUser("Yasin", null);
        }, "attachWebsiteToUser with null website works");
    }
    
    @Test
    @DisplayName("attachNotificationChannel with valid parameters should not throw exception")
    void testAttachNotificationChannel() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        ResponseChannel newChannel = new SmsChannel();
        assertDoesNotThrow(() -> {
            webMonitor.attachNotificationChannel("Yasin", newChannel);
        }, "attachNotificationChannel works");
    }
    
    @Test
    @DisplayName("attachNotificationChannel with null name should work")
    void testAttachNotificationChannelWithNullName() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        ResponseChannel newChannel = new SmsChannel();
        assertDoesNotThrow(() -> {
            webMonitor.attachNotificationChannel(null, newChannel);
        }, "attachNotificationChannel with null name works");
    }
    
    @Test
    @DisplayName("attachNotificationChannel with null channel should work")
    void testAttachNotificationChannelWithNullChannel() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        assertDoesNotThrow(() -> {
            webMonitor.attachNotificationChannel("Yasin", null);
        }, "attachNotificationChannel with null channel works");
    }
    
    @Test
    @DisplayName("setWebsiteStrategy should not throw exception")
    void testSetWebsiteStrategy() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        ComparisonStrategy strategy = new TextContentStrategy();
        assertDoesNotThrow(() -> {
            webMonitor.setWebsiteStrategy(testWebsite, strategy);
        }, "setWebsiteStrategy works");
    }
    
    @Test
    @DisplayName("setWebsiteStrategy with null website should work")
    void testSetWebsiteStrategyWithNullWebsite() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        ComparisonStrategy strategy = new TextContentStrategy();
        assertDoesNotThrow(() -> {
            webMonitor.setWebsiteStrategy(null, strategy);
        }, "setWebsiteStrategy with null website works");
    }
    
    @Test
    @DisplayName("setWebsiteStrategy with null strategy should work")
    void testSetWebsiteStrategyWithNullStrategy() {
        webMonitor.addNewUser("Yasin", 5, testWebsite, testChannel);
        
        assertDoesNotThrow(() -> {
            webMonitor.setWebsiteStrategy(testWebsite, null);
        }, "setWebsiteStrategy with null strategy works");
    }
} 