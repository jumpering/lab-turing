package util.view.dialog.primitive;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class RegexRule {

    private final String displayName;
    private final Pattern pattern;

    public RegexRule(String displayName, Pattern pattern) {
        this.displayName = Objects.requireNonNull(displayName, "key must not be null");
        this.pattern = Objects.requireNonNull(pattern, "pattern must not be null");
    }

    public String getDisplayName() {
        return displayName;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static RegexRule sequence(
        RegexRule prefix,
        RegexRule element,
        RegexRule separator,
        RegexRule postfix
    ) {
        String displayName = prefix.getDisplayName()
                        + element.getDisplayName()
                        + "(" + separator.getDisplayName() + element.getDisplayName() + ")*"
                        + postfix.getDisplayName();

        String regex = prefix.getPattern().pattern()
                    + element.getPattern().pattern()
                    + "(" + Pattern.quote(separator.getPattern().pattern())
                    + element.getPattern().pattern() + ")*?"
                    + postfix.getPattern().pattern();

        return new RegexRule(displayName, Pattern.compile(regex));
    }

    public static RegexRule interval(
        RegexRule prefix,
        RegexRule element,
        RegexRule separator,
        RegexRule postfix
    ) {
        String displayName = prefix.getDisplayName()
                        + element.getDisplayName()
                        + separator.getDisplayName()
                        + element.getDisplayName()
                        + postfix.getDisplayName();

        String regex = prefix.getPattern().pattern()
                    + element.getPattern().pattern()
                    + Pattern.quote(separator.getPattern().pattern())
                    + element.getPattern().pattern()
                    + postfix.getPattern().pattern();

        return new RegexRule(displayName, Pattern.compile(regex));
    }

    public static RegexRule pair(
        RegexRule prefix,
        RegexRule keyElement,
        RegexRule separator,
        RegexRule valueElement,
        RegexRule postfix
    ) {
        String displayName = prefix.getDisplayName()
                        + keyElement.getDisplayName()
                        + separator.getDisplayName()
                        + valueElement.getDisplayName()
                        + postfix.getDisplayName();

        String regex = prefix.getPattern().pattern()
                    + keyElement.getPattern().pattern()
                    + Pattern.quote(separator.getPattern().pattern())
                    + valueElement.getPattern().pattern()
                    + postfix.getPattern().pattern();

        return new RegexRule(displayName, Pattern.compile(regex));
    }

    public static final RegexRule EMPTY = new RegexRule(
            "",
            Pattern.compile(""));

    public static final RegexRule CHAR_RULE = new RegexRule(
            "CHAR", Pattern.compile("c") 
    );

    public static final RegexRule INTEGER_RULE = new RegexRule(
            "INTEGER", Pattern.compile("-?\\d+")
    );

    public static final RegexRule DOUBLE_RULE = new RegexRule(
            "DOUBLE", Pattern.compile(
                    "-?(\\d+(\\.\\d+)?([eE][+-]?\\d+)?|\\.\\d+([eE][+-]?\\d+)?)")
                    );

    @Override
    public String toString() {
        return displayName + " («" + pattern.pattern() + "»)";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RegexRule other = (RegexRule) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (pattern == null) {
            if (other.pattern != null)
                return false;
        } else if (!pattern.equals(other.pattern))
            return false;
        return true;
    }

}
