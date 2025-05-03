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

    private static RegexRule compose(String displayName, String regex) {
        return new RegexRule(displayName, Pattern.compile(regex));
    }

    public static RegexRule sequence(RegexRule... parts) {
        String displayName = Arrays.stream(parts)
                .map(RegexRule::getDisplayName)
                .collect(Collectors.joining());
        String regex = Arrays.stream(parts)
                .map(r -> r.getPattern().pattern())
                .collect(Collectors.joining());
        return compose(displayName, regex);
    }

    public static RegexRule interval(
            RegexRule elementRule,
            RegexRule separatorRule,
            RegexRule openBracketRule,
            RegexRule closeBracketRule) {
        String displayName = openBracketRule.getDisplayName()
                + elementRule.getDisplayName()
                + separatorRule.getDisplayName()
                + elementRule.getDisplayName()
                + closeBracketRule.getDisplayName();

        String regexPattern = openBracketRule.getPattern().pattern()
                + "(" + elementRule.getPattern().pattern()
                + "(" + separatorRule.getPattern().pattern()
                + elementRule.getPattern().pattern()
                + ")*)?"
                + closeBracketRule.getPattern().pattern();

        return compose(displayName, regexPattern);
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

    public static final RegexRule OPEN_BRACKET = new RegexRule(
            "[", Pattern.compile("\\[")
    );

    public static final RegexRule CLOSE_BRACKET = new RegexRule(
            "]", Pattern.compile("\\]")
    );

    public static final RegexRule COMMA = new RegexRule(
            ",", Pattern.compile(",")
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
