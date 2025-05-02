package util.view.dialog.values;

import util.collection.list.LinkedList;
import util.values.Interval;
import util.values.Pair;
import util.view.dialog.primitive.SecuenceDialog;

public abstract class IntervalDialog<T extends Comparable<T>> extends SecuenceDialog<Interval<T>> {

    protected IntervalDialog(String title, Pair<String,String> regExp) {
        super(title,  new Pair<String,String> ("[","\\[") , regExp , "," , new Pair<String,String> ("]","\\]"));
    }

    protected boolean isSemanticValid(String string){
        LinkedList<T> values = this.values(string);
        return values.size() != 2;
    }

    protected abstract LinkedList<T> values(String string);

    protected IntervalDialog(Pair<String,String> regExp) {
        this("", regExp);
    }

}
