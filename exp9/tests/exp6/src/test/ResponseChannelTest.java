import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseChannelTest {
    
    private MailChannel mailChannel;
    private SmsChannel smsChannel;
    
    @BeforeEach
    void setUp() {
        mailChannel = new MailChannel();
        smsChannel = new SmsChannel();
    }
    
    @Test
    @DisplayName("MailChannel should be created successfully")
    void testMailChannelConstructor() {
        assertNotNull(mailChannel, "MailChannel was created");
    }
    
    @Test
    @DisplayName("SmsChannel should be created successfully")
    void testSmsChannelConstructor() {
        assertNotNull(smsChannel, "SmsChannel was created");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with valid input should not throw exception")
    void testMailChannelSendNotificationWithValidInput() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", "Test message");
        }, "MailChannel with valid input works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with null recipient should not throw exception")
    void testMailChannelSendNotificationWithNullRecipient() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification(null, "Test message");
        }, "MailChannel with null recipient works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with null message should not throw exception")
    void testMailChannelSendNotificationWithNullMessage() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", null);
        }, "MailChannel with null message works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with empty recipient should not throw exception")
    void testMailChannelSendNotificationWithEmptyRecipient() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("", "Test message");
        }, "MailChannel with empty recipient works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with empty message should not throw exception")
    void testMailChannelSendNotificationWithEmptyMessage() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", "");
        }, "MailChannel with empty message works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with very long message should not throw exception")
    void testMailChannelSendNotificationWithLongMessage() {
        String longMessage = "This is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long messageThis is a very long message";
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", longMessage);
        }, "MailChannel with long message works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with special characters should not throw exception")
    void testMailChannelSendNotificationWithSpecialCharacters() {
        String specialMessage = "Message with special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", specialMessage);
        }, "MailChannel with special characters works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with unicode should not throw exception")
    void testMailChannelSendNotificationWithUnicode() {
        String unicodeMessage = "Message with unicode: 你好世界 🌍 🚀";
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("yasin@gmail.com", unicodeMessage);
        }, "MailChannel with unicode works");
    }
    
    @Test
    @DisplayName("MailChannel sendNotification with special email addresses should not throw exception")
    void testMailChannelSendNotificationWithSpecialEmailAddresses() {
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("test+tag@example.com", "Test message");
            mailChannel.sendNotification("user.name@domain.co.uk", "Test message");
            mailChannel.sendNotification("123@456.789", "Test message");
        }, "MailChannel with special email addresses works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with valid input should not throw exception")
    void testSmsChannelSendNotificationWithValidInput() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", "Test message");
        }, "SmsChannel with valid input works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with null recipient should not throw exception")
    void testSmsChannelSendNotificationWithNullRecipient() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification(null, "Test message");
        }, "SmsChannel with null recipient works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with null message should not throw exception")
    void testSmsChannelSendNotificationWithNullMessage() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", null);
        }, "SmsChannel with null message works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with empty recipient should not throw exception")
    void testSmsChannelSendNotificationWithEmptyRecipient() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("", "Test message");
        }, "SmsChannel with empty recipient works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with empty message should not throw exception")
    void testSmsChannelSendNotificationWithEmptyMessage() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", "");
        }, "SmsChannel with empty message works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with very long message should not throw exception")
    void testSmsChannelSendNotificationWithLongMessage() {
        String longMessage = "very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message. very long message.";
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", longMessage);
        }, "SmsChannel with long message works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with special characters should not throw exception")
    void testSmsChannelSendNotificationWithSpecialCharacters() {
        String specialMessage = "SMS with special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", specialMessage);
        }, "SmsChannel with special characters works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with unicode should not throw exception")
    void testSmsChannelSendNotificationWithUnicode() {
        String unicodeMessage = "SMS with unicode: 你好世界 🌍 🚀";
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("+1234567890", unicodeMessage);
        }, "SmsChannel with unicode works");
    }
    
    @Test
    @DisplayName("SmsChannel sendNotification with different phone number formats should not throw exception")
    void testSmsChannelSendNotificationWithDifferentPhoneFormats() {
        assertDoesNotThrow(() -> {
            smsChannel.sendNotification("1234567890", "Test message");
            smsChannel.sendNotification("+1-234-567-8900", "Test message");
            smsChannel.sendNotification("(123) 456-7890", "Test message");
            smsChannel.sendNotification("123.456.7890", "Test message");
        }, "SmsChannel with different phone formats works");
    }
    
    @Test
    @DisplayName("MailChannel should implement ResponseChannel interface")
    void testMailChannelImplementsInterface() {
        assertTrue(mailChannel instanceof ResponseChannel, "MailChannel implements ResponseChannel");
    }
    
    @Test
    @DisplayName("SmsChannel should implement ResponseChannel interface")
    void testSmsChannelImplementsInterface() {
        assertTrue(smsChannel instanceof ResponseChannel, "SmsChannel implements ResponseChannel");
    }
    
    @Test
    @DisplayName("Both channels should handle null parameters gracefully")
    void testChannelsHandleNullsGracefully() {
        // Test both channels with null parameters
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification(null, null);
            smsChannel.sendNotification(null, null);
        }, "Both channels handle null parameters");
    }
    
    @Test
    @DisplayName("Both channels should handle empty parameters gracefully")
    void testChannelsHandleEmptyParametersGracefully() {
        // Test both channels with empty parameters
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification("", "");
            smsChannel.sendNotification("", "");
        }, "Both channels handle empty parameters");
    }
    
    @Test
    @DisplayName("Both channels should handle mixed null and empty parameters")
    void testChannelsHandleMixedParameters() {
        // Test both channels with mixed null and empty parameters
        assertDoesNotThrow(() -> {
            mailChannel.sendNotification(null, "");
            mailChannel.sendNotification("", null);
            smsChannel.sendNotification(null, "");
            smsChannel.sendNotification("", null);
        }, "Both channels handle mixed parameters");
    }
} 