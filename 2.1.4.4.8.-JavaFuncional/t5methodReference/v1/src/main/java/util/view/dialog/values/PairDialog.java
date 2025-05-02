package util.view.dialog.values;

import util.collection.list.LinkedList;
import util.values.Pair;
import util.view.dialog.primitive.Dialog;

public abstract class PairDialog<K, V> extends Dialog<Pair<K, V>> {
// Añadir string con traducción humana
// Cambiar las *RegExp de String a un nuevo tipo PairRegExp<String,String>
// para tener la clave humana y el valor   
  private static final String PREFIX = "<";
  private static final String SEPARATOR = "\\|";
  private static final String POSTFIX = ">";
  private static final String FIXES = "[" + PREFIX + POSTFIX + "]";

  protected PairDialog(String title, Pair<String,String> keyRegExp, Pair<String,String> valueRegExp) {
    super(title, new Pair<String,String> (PREFIX  + keyRegExp.getKey() + "|"  + valueRegExp.getKey() + POSTFIX , PREFIX + "(" + keyRegExp.getValue() + ")" + SEPARATOR + "(" + valueRegExp.getValue() + ")" + POSTFIX));
  }

  protected PairDialog(Pair<String,String> keyRegExp, Pair<String,String> valueRegExp) {
    this("", keyRegExp, valueRegExp);
  }

  protected boolean isSemanticValid(String string) {
    LinkedList<String> values = this.strings(string);
    return values.size() == 2 
        && new DateDialog().isSemanticValid(values.get(0))
        && new TimeIntervalDialog().isSemanticValid(values.get(1));
  }

  protected LinkedList<String> strings(String string) {
    LinkedList<String> strings = new LinkedList<String>();
    string = string.replaceAll(FIXES, "");
    if (string.isBlank()) {
      return strings;
    }
    for (String element : string.split(SEPARATOR)) {
      strings.add(element);
    }
    return strings;
  }

}
