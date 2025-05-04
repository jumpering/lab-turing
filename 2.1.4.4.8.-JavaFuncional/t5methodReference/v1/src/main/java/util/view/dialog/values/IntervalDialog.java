package util.view.dialog.values;

import java.util.regex.Pattern;

import util.collection.list.LinkedList;
import util.values.Interval;
import util.view.dialog.primitive.RegexRule;
import util.view.dialog.primitive.Dialog;

public abstract class IntervalDialog<T extends Comparable<T>> extends Dialog<Interval<T>> {

    private static final RegexRule PREFIX_RULE    = new RegexRule("[", Pattern.compile("\\["));
    private static final RegexRule POSTFIX_RULE   = new RegexRule("]", Pattern.compile("\\]"));
    private static final RegexRule SEPARATOR_RULE = new RegexRule(",", Pattern.compile("\\,"));
    private static final String FIXES = "[" + PREFIX_RULE.getDisplayName() + POSTFIX_RULE.getDisplayName() + "]";

    protected IntervalDialog(String title, RegexRule regExp) {
        super(title, RegexRule.interval( PREFIX_RULE, regExp, SEPARATOR_RULE, POSTFIX_RULE));
    }

    protected boolean isSemanticValid(String string){
        LinkedList<T> values = this.values(string);
        return values.size() == 2;
    }

    @Override
    protected String errorSemanticMsg() {
    return "Intervalo debe contener exactamente 2 valores";
    }


    protected LinkedList<String> strings(String string) {
        LinkedList<String> strings = new LinkedList<String>();
        if (!FIXES.equals("[]")){
            string = string.replaceAll(FIXES, "");
        }
        if (string.isBlank()) {
            return strings;
        }
        for (String element : string.split(SEPARATOR_RULE.getDisplayName())) {
            strings.add(element);
        }
        return strings;
    }

    protected abstract LinkedList<T> values(String string);

    protected IntervalDialog(RegexRule regExp) {
        this("", regExp);
    }

}
