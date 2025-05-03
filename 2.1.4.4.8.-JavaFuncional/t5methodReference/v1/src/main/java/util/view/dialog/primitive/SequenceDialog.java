package util.view.dialog.primitive;

import util.collection.list.LinkedList;

public abstract class SequenceDialog<T> extends Dialog<T> {
    
    private final String FIXES;
    private final String SEPARATOR;
    
    protected SequenceDialog(String title, RegexRule prefix, RegexRule element, RegexRule separator, RegexRule  postfix) {
        super(title, RegexRule.sequence( prefix, element, separator, postfix));
        this.FIXES = "[" + prefix.getDisplayName() + postfix.getDisplayName() + "]";
        this.SEPARATOR = separator.getDisplayName();
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
