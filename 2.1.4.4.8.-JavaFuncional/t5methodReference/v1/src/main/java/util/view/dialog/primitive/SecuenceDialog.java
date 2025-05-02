package util.view.dialog.primitive;

import util.collection.list.LinkedList;
import util.values.Pair;

public abstract class SecuenceDialog<T> extends Dialog<T> {
    
    private final String FIXES;
    private final String SEPARATOR;
    
    protected SecuenceDialog(String title, Pair<String,String> prefix, Pair<String,String> element, String separator, Pair<String,String>  postfix) {
        super(title, new Pair<String,String> (prefix.getKey() + element.getKey() + separator + element.getKey() + "(n veces opcional)" + postfix.getKey(), prefix.getValue() + "(" + element.getValue() + "(" + separator + element.getValue() + ")*)?" + postfix.getValue()));
        this.FIXES = "[" + prefix.getKey() + postfix.getKey() + "]";
        this.SEPARATOR = separator;
    }

    protected LinkedList<String> strings(String string) {
        LinkedList<String> strings = new LinkedList<String>();
        if (!this.FIXES.equals("[]")){
            string = string.replaceAll(this.FIXES, "");
        }
        if (string.isBlank()) {
            return strings;
        }
        for (String element : string.split(this.SEPARATOR)) {
            strings.add(element);
        }
        return strings;
    }
    
}
