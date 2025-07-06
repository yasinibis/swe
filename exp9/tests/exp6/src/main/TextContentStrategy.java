public class TextContentStrategy implements ComparisonStrategy {
    @Override
    public boolean compare(String oldContent, String newContent) {
        String normalizedOld = normalizeText(oldContent);
        String normalizedNew = normalizeText(newContent);
        return normalizedOld.equals(normalizedNew);
    }

    private String normalizeText(String content) {
        // Remove HTML tags
        String withoutTags = content.replaceAll("<[^>]*>", "");
        // Normalize whitespace
        return withoutTags.replaceAll("\\s+", " ").trim();
    }

    @Override
    public String getStrategyName() {
        return "Text Content Comparison";
    }
} 