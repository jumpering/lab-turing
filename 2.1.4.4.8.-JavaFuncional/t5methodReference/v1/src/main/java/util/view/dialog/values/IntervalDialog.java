package util.view.dialog.values;

import util.collection.list.LinkedList;
import util.values.Interval;
import util.view.dialog.primitive.RegexRule;
import util.view.dialog.primitive.SequenceDialog;

public abstract class IntervalDialog<T extends Comparable<T>> extends SequenceDialog<Interval<T>> {

    protected IntervalDialog(String title, RegexRule regExp) {
        super(title, RegexRule.OPEN_BRACKET, regExp, RegexRule.COMMA, RegexRule.CLOSE_BRACKET
        );
    }

    protected boolean isSemanticValid(String string){
        LinkedList<T> values = this.values(string);
        return values.size() != 2;
    }

    protected abstract LinkedList<T> values(String string);

    protected IntervalDialog(RegexRule regExp) {
        this("", regExp);
    }

}
