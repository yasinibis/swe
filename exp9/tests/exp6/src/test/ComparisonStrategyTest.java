import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ComparisonStrategyTest {
    
    private ContentSizeStrategy contentSizeStrategy;
    private TextContentStrategy textContentStrategy;
    private HtmlContentStrategy htmlContentStrategy;
    
    @BeforeEach
    void setUp() {
        contentSizeStrategy = new ContentSizeStrategy();
        textContentStrategy = new TextContentStrategy();
        htmlContentStrategy = new HtmlContentStrategy();
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should compare strings by length correctly")
    void testContentSizeStrategyCompare() {
        // Same length
        assertTrue(contentSizeStrategy.compare("Hello", "World"), "Same length strings return true");
        assertTrue(contentSizeStrategy.compare("", ""), "Empty strings return true");
        assertTrue(contentSizeStrategy.compare(null, null), "Null strings return true");
        
        // Different length
        assertFalse(contentSizeStrategy.compare("Hello", "Hello World"), "Different length strings return false");
        assertFalse(contentSizeStrategy.compare("", "Hello"), "Empty vs non-empty returns false");
        assertFalse(contentSizeStrategy.compare(null, "Hello"), "Null vs non-null returns false");
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should handle very long strings")
    void testContentSizeStrategyWithLongStrings() {
        String longString1 = "This is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many words";
        String longString2 = "This is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many words";
        
        assertTrue(contentSizeStrategy.compare(longString1, longString2), "Same length long strings return true");
        assertFalse(contentSizeStrategy.compare(longString1, longString1 + " extra"), "Different length long strings return false");
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should handle special characters")
    void testContentSizeStrategyWithSpecialCharacters() {
        String special1 = "Hello!@#$%^&*()_+-=[]{}|;':\",./<>?";
        String special2 = "World!@#$%^&*()_+-=[]{}|;':\",./<>?";
        
        assertTrue(contentSizeStrategy.compare(special1, special2), "Same length special chars return true");
        assertFalse(contentSizeStrategy.compare(special1, special1 + "!"), "Different length special chars return false");
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should handle unicode characters")
    void testContentSizeStrategyWithUnicode() {
        String unicode1 = "Hello 你好世界 🌍";
        String unicode2 = "World 世界你好 🚀";
        
        assertTrue(contentSizeStrategy.compare(unicode1, unicode2), "Same length unicode strings return true");
        assertFalse(contentSizeStrategy.compare(unicode1, unicode1 + "🌍"), "Different length unicode strings return false");
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should return correct strategy name")
    void testContentSizeStrategyGetStrategyName() {
        String name = contentSizeStrategy.getStrategyName();
        assertEquals("Content Size Comparison", name, "ContentSizeStrategy returns correct name");
    }
    
    @Test
    @DisplayName("TextContentStrategy should compare text content correctly")
    void testTextContentStrategyCompare() {
        // Identical text
        assertTrue(textContentStrategy.compare("Hello World", "Hello World"), "Identical text returns true");
        
        // Different text
        assertFalse(textContentStrategy.compare("Hello World", "Goodbye World"), "Different text returns false");
        
        // HTML with same text content
        assertTrue(textContentStrategy.compare("<html><body>Hello World</body></html>", "<div><p>Hello World</p></div>"), 
                  "HTML with same text content returns true");
        
        // Whitespace normalization
        assertTrue(textContentStrategy.compare("Hello   World", "Hello World"), "Whitespace normalization works");
    }
    
    @Test
    @DisplayName("TextContentStrategy should handle very long text")
    void testTextContentStrategyWithLongText() {
        String longText1 = "This is a very long text with many words to test if the text content strategy can handle long text properly without any issues";
        String longText2 = "This is a very long text with many words to test if the text content strategy can handle long text properly without any issues";
        
        assertTrue(textContentStrategy.compare(longText1, longText2), "Same long text returns true");
        assertFalse(textContentStrategy.compare(longText1, longText1 + " extra"), "Different long text returns false");
    }
    
    @Test
    @DisplayName("TextContentStrategy should handle special characters in text")
    void testTextContentStrategyWithSpecialCharacters() {
        String special1 = "Hello!@#$%^&*()_+-=[]{}|;':\",./<>? World";
        String special2 = "Hello!@#$%^&*()_+-=[]{}|;':\",./<>? World";
        
        assertTrue(textContentStrategy.compare(special1, special2), "Same special chars text returns true");
        assertFalse(textContentStrategy.compare(special1, special1 + "!"), "Different special chars text returns false");
    }
    
    @Test
    @DisplayName("TextContentStrategy should handle unicode text")
    void testTextContentStrategyWithUnicode() {
        String unicode1 = "Hello 你好世界 🌍 World";
        String unicode2 = "Hello 你好世界 🌍 World";
        
        assertTrue(textContentStrategy.compare(unicode1, unicode2), "Same unicode text returns true");
        assertFalse(textContentStrategy.compare(unicode1, unicode1 + "🚀"), "Different unicode text returns false");
    }
    
    @Test
    @DisplayName("TextContentStrategy should handle complex HTML")
    void testTextContentStrategyWithComplexHTML() {
        String html1 = "<html><head><title>Test</title></head><body><h1>Hello</h1><p>World</p><div><span>Content</span></div></body></html>";
        String html2 = "<div><header><h1>Hello</h1></header><main><p>World</p><section><span>Content</span></section></main></div>";
        
        assertTrue(textContentStrategy.compare(html1, html2), "Complex HTML with same text returns true");
    }
    
    @Test
    @DisplayName("TextContentStrategy should return correct strategy name")
    void testTextContentStrategyGetStrategyName() {
        String name = textContentStrategy.getStrategyName();
        assertEquals("Text Content Comparison", name, "TextContentStrategy returns correct name");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should compare HTML content correctly")
    void testHtmlContentStrategyCompare() {
        // Identical HTML
        assertTrue(htmlContentStrategy.compare("<html><body>Hello</body></html>", "<html><body>Hello</body></html>"), 
                  "Identical HTML returns true");
        
        // Different HTML
        assertFalse(htmlContentStrategy.compare("<html><body>Hello</body></html>", "<html><body>Goodbye</body></html>"), 
                   "Different HTML returns false");
        
        // HTML with different tags but same content
        assertTrue(htmlContentStrategy.compare("<html><body>Hello</body></html>", "<div><p>Hello</p></div>"), 
                  "HTML with different tags but same content returns true");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should handle very long HTML")
    void testHtmlContentStrategyWithLongHTML() {
        String longHtml1 = "<html><head><title>Very Long Title</title></head><body><h1>Very Long Heading</h1><p>This is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many words</p><div><span>More content</span></div></body></html>";
        String longHtml2 = "<html><head><title>Very Long Title</title></head><body><h1>Very Long Heading</h1><p>This is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many wordsThis is a very long paragraph with many words</p><div><span>More content</span></div></body></html>";
        
        assertTrue(htmlContentStrategy.compare(longHtml1, longHtml2), "Same long HTML returns true");
        assertFalse(htmlContentStrategy.compare(longHtml1, longHtml1 + "<p>Extra</p>"), "Different long HTML returns false");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should handle special characters in HTML")
    void testHtmlContentStrategyWithSpecialCharacters() {
        String special1 = "<html><body><h1>Hello!@#$%^&*()_+-=[]{}|;':\",./<>?</h1><p>World</p></body></html>";
        String special2 = "<div><header><h1>Hello!@#$%^&*()_+-=[]{}|;':\",./<>?</h1></header><main><p>World</p></main></div>";
        
        assertTrue(htmlContentStrategy.compare(special1, special2), "HTML with same special chars returns true");
        assertFalse(htmlContentStrategy.compare(special1, special1 + "<p>Extra!</p>"), "HTML with different special chars returns false");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should handle unicode in HTML")
    void testHtmlContentStrategyWithUnicode() {
        String unicode1 = "<html><body><h1>Hello 你好世界 🌍</h1><p>World 🚀</p></body></html>";
        String unicode2 = "<div><header><h1>Hello 你好世界 🌍</h1></header><main><p>World 🚀</p></main></div>";
        
        assertTrue(htmlContentStrategy.compare(unicode1, unicode2), "HTML with same unicode returns true");
        assertFalse(htmlContentStrategy.compare(unicode1, unicode1 + "<p>Extra 🌍</p>"), "HTML with different unicode returns false");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should handle plain text")
    void testHtmlContentStrategyWithPlainText() {
        String plain1 = "Hello World";
        String plain2 = "Hello World";
        
        assertTrue(htmlContentStrategy.compare(plain1, plain2), "Same plain text returns true");
        assertFalse(htmlContentStrategy.compare(plain1, plain1 + " extra"), "Different plain text returns false");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should return correct strategy name")
    void testHtmlContentStrategyGetStrategyName() {
        String name = htmlContentStrategy.getStrategyName();
        assertEquals("HTML Content Comparison", name, "HtmlContentStrategy returns correct name");
    }
    
    @Test
    @DisplayName("All strategies should implement ComparisonStrategy interface")
    void testStrategiesImplementInterface() {
        assertTrue(contentSizeStrategy instanceof ComparisonStrategy, "ContentSizeStrategy implements ComparisonStrategy");
        assertTrue(textContentStrategy instanceof ComparisonStrategy, "TextContentStrategy implements ComparisonStrategy");
        assertTrue(htmlContentStrategy instanceof ComparisonStrategy, "HtmlContentStrategy implements ComparisonStrategy");
    }
    
    @Test
    @DisplayName("ContentSizeStrategy should handle null parameters gracefully")
    void testContentSizeStrategyWithNulls() {
        assertTrue(contentSizeStrategy.compare(null, null), "Both null returns true");
        assertFalse(contentSizeStrategy.compare(null, "Hello"), "One null returns false");
        assertFalse(contentSizeStrategy.compare("Hello", null), "One null returns false");
    }
    
    @Test
    @DisplayName("TextContentStrategy should handle null parameters gracefully")
    void testTextContentStrategyWithNulls() {
        assertTrue(textContentStrategy.compare(null, null), "Both null returns true");
        assertFalse(textContentStrategy.compare(null, "Hello"), "One null returns false");
        assertFalse(textContentStrategy.compare("Hello", null), "One null returns false");
    }
    
    @Test
    @DisplayName("HtmlContentStrategy should handle null parameters gracefully")
    void testHtmlContentStrategyWithNulls() {
        assertTrue(htmlContentStrategy.compare(null, null), "Both null returns true");
        assertFalse(htmlContentStrategy.compare(null, "<html>Hello</html>"), "One null returns false");
        assertFalse(htmlContentStrategy.compare("<html>Hello</html>", null), "One null returns false");
    }
} 