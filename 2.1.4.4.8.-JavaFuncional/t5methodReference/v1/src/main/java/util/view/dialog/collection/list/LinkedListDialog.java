package util.view.dialog.collection.list;

import java.util.regex.Pattern;

import util.view.dialog.primitive.RegexRule;
import util.view.dialog.primitive.SequenceDialog;

public abstract class LinkedListDialog<T> extends SequenceDialog<T> {

    private static final RegexRule PREFIX_RULE    = new RegexRule("{",Pattern.compile("\\{"));
    private static final RegexRule POSTFIX_RULE   = new RegexRule("}",Pattern.compile("\\}"));
    private static final RegexRule SEPARATOR_RULE = new RegexRule(",", Pattern.compile("\\,"));

    protected LinkedListDialog(String title, RegexRule regExp) {
        super(title, PREFIX_RULE, regExp, SEPARATOR_RULE,  POSTFIX_RULE);
    }

}
