package util.view.dialog.collection.list;

import util.values.Pair;
import util.view.dialog.primitive.SecuenceDialog;

public abstract class LinkedListDialog<T> extends SecuenceDialog<T> {

    protected LinkedListDialog(String title, Pair<String,String> regExp) {
        super(title, new Pair<String,String> ("{","\\{"), regExp, ",",  new Pair<String,String> ("}","\\}"));
    }

}
