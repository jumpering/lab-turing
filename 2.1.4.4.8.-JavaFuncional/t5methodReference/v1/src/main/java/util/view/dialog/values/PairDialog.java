package util.view.dialog.values;

import java.util.regex.Pattern;

import util.collection.list.LinkedList;
import util.values.Pair;
import util.view.dialog.primitive.Dialog;
import util.view.dialog.primitive.RegexRule;

public abstract class PairDialog<K, V> extends Dialog<Pair<K, V>> {

  private static final RegexRule PREFIX_RULE    = new RegexRule("<", Pattern.compile("\\<"));
  private static final RegexRule POSTFIX_RULE   = new RegexRule(">", Pattern.compile("\\>"));
  private static final RegexRule SEPARATOR_RULE = new RegexRule("|", Pattern.compile("\\|"));
  private static final String FIXES = "[" + PREFIX_RULE.getDisplayName() + POSTFIX_RULE.getDisplayName() + "]";

  protected PairDialog(String title, RegexRule keyRule, RegexRule valueRule) {
    super(title, RegexRule.pair(
      PREFIX_RULE,
      keyRule,
      SEPARATOR_RULE,
      valueRule,
      POSTFIX_RULE
  ));
  }

  protected PairDialog(RegexRule keyRule, RegexRule valueRule) {
    this("", keyRule, valueRule);
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
    for (String element : string.split(SEPARATOR_RULE.getDisplayName())) {
      strings.add(element);
    }
    return strings;
  }

}
