package util.view.dialog.values;

import java.util.regex.Pattern;

import util.collection.Iterator;
import util.collection.list.LinkedList;
import util.values.Time;
import util.view.dialog.primitive.IntDialog;
import util.view.dialog.primitive.RegexRule;
import util.view.dialog.primitive.SequenceDialog;

public class TimeDialog extends SequenceDialog<Time> {

    public TimeDialog(String title) {
        super(title, RegexRule.EMPTY, new IntDialog().regExp(),new RegexRule(":", Pattern.compile(":")), RegexRule.EMPTY);
    }

    public TimeDialog() {
        this("");
    }

    public boolean isSemanticValid(String string) {
        LinkedList<Integer> values = this.values(string);
        return Time.isValidHour(values.get(0))
                && Time.isValidMinute(values.get(1))
                && Time.isValidSeconds(values.get(2))
                && values.size() == 3;
    }

    public String errorSemanticMsg() {
        return " no hay 3 valores o la hora, minuto o segundo están fuera de rango [0-23] y [0-59] respectivamente";
    }

    public Time create(String string) {
        LinkedList<Integer> values = this.values(string);
        return new Time(values.get(0), values.get(1), values.get(2));
    }

    private LinkedList<Integer> values(String string) {        
        LinkedList<Integer> intList = new LinkedList<Integer>();
        Iterator<String> iterator = this.strings(string).iterator();
        while (iterator.hasNext()) {
            intList.add(new IntDialog().create(iterator.next()));
        }
        return intList;
    }

    public void addContent(Time time) {
        this.addLine("next: " + time.next());
        Time pivot = new Time(12, 30, 0);
        this.addLine("before " + pivot + ": " + time.before(pivot));
        this.addLine("equals " + pivot + ": " + time.equals(pivot));
        this.addLine("after " + pivot + ": " + time.after(pivot));
    }

}
