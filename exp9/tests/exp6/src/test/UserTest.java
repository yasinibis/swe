import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    
    private ResponseChannel testChannel;
    private User user;
    
    @BeforeEach
    void setUp() {
        testChannel = new MailChannel();
        user = new User("Yasin", 5, testChannel);
    }
    
    @Test
    @DisplayName("User constructor with valid input should create user successfully")
    void testUserConstructorWithValidInput() {
        assertNotNull(user, "User was created");
    }
    
    @Test
    @DisplayName("User constructor with null name should throw NullPointerException")
    void testUserConstructorWithNullName() {
        assertThrows(NullPointerException.class, () -> {
            new User(null, 5, testChannel);
        }, "User with null name throws exception");
    }
    
    @Test
    @DisplayName("User constructor with empty name should work")
    void testUserConstructorWithEmptyName() {
        User emptyNameUser = new User("", 5, testChannel);
        assertNotNull(emptyNameUser, "User with empty name was created");
    }
    
    @Test
    @DisplayName("User constructor with zero frequency should work")
    void testUserConstructorWithZeroFrequency() {
        User zeroFreqUser = new User("Yasin", 0, testChannel);
        assertNotNull(zeroFreqUser, "User with zero frequency was created");
    }
    
    @Test
    @DisplayName("User constructor with negative frequency should work")
    void testUserConstructorWithNegativeFrequency() {
        User negativeFreqUser = new User("Yasin", -1, testChannel);
        assertNotNull(negativeFreqUser, "User with negative frequency was created");
    }
    
    @Test
    @DisplayName("User constructor with null channel should create user successfully")
    void testUserConstructorWithNullChannel() {
        User nullChannelUser = new User("Yasin", 5, null);
        assertNotNull(nullChannelUser, "User with null channel was created");
    }
    
    @Test
    @DisplayName("addResponseChannel with valid channel should not throw exception")
    void testAddResponseChannelWithValidChannel() {
        ResponseChannel newChannel = new SmsChannel();
        assertDoesNotThrow(() -> {
            user.addResponseChannel(newChannel);
        }, "addResponseChannel with valid channel works");
    }
    
    @Test
    @DisplayName("addResponseChannel with null channel should not throw exception")
    void testAddResponseChannelWithNullChannel() {
        assertDoesNotThrow(() -> {
            user.addResponseChannel(null);
        }, "addResponseChannel with null channel works");
    }
    
    @Test
    @DisplayName("update with valid message should not throw exception")
    void testUpdateWithValidMessage() {
        assertDoesNotThrow(() -> {
            user.update("Test message");
        }, "update with valid message works");
    }
    
    @Test
    @DisplayName("update with null message should not throw exception")
    void testUpdateWithNullMessage() {
        assertDoesNotThrow(() -> {
            user.update(null);
        }, "update with null message works");
    }
    
    @Test
    @DisplayName("update with empty message should not throw exception")
    void testUpdateWithEmptyMessage() {
        assertDoesNotThrow(() -> {
            user.update("");
        }, "update with empty message works");
    }
    
    @Test
    @DisplayName("update with long message should not throw exception")
    void testUpdateWithLongMessage() {
        String longMessage = "long messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong messagelong message";
        assertDoesNotThrow(() -> {
            user.update(longMessage);
        }, "update with long message works");
    }
    
    @Test
    @DisplayName("update with special characters should not throw exception")
    void testUpdateWithSpecialCharacters() {
        String specialMessage = "Message with special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        assertDoesNotThrow(() -> {
            user.update(specialMessage);
        }, "update with special characters works");
    }
    
    @Test
    @DisplayName("update with unicode characters should not throw exception")
    void testUpdateWithUnicodeCharacters() {
        String unicodeMessage = "Message with unicode: 你好世界 🌍 🚀";
        assertDoesNotThrow(() -> {
            user.update(unicodeMessage);
        }, "update with unicode characters works");
    }
    
    @Test
    @DisplayName("User should implement Observer interface")
    void testUserImplementsObserver() {
        assertTrue(user instanceof Observer, "User implements Observer");
    }
} 