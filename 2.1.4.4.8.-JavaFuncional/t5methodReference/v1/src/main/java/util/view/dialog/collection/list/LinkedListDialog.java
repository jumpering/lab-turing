package util.view.dialog.collection.list;

import java.util.regex.Pattern;

import util.view.dialog.primitive.RegexRule;
import util.view.dialog.primitive.SequenceDialog;

public abstract class LinkedListDialog<T> extends SequenceDialog<T> {

    protected LinkedListDialog(String title, RegexRule regExp) {
        super(title, new RegexRule("{",Pattern.compile("\\{")), regExp, RegexRule.COMMA,  new RegexRule("}",Pattern.compile("\\}")));
    }

}
