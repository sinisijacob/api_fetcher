package api_fetcher;

public enum Language_Options {
    ENGLISH("english"),
    RUSSIAN("russian"),
    ITALIAN("italian");

    private final String displayName;

    Language_Options(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Language_Options fromString(String text) {
        for (Language_Options lang : Language_Options.values()) {
            if (lang.displayName.equalsIgnoreCase(text)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
