package util.view.dialog.primitive;

public abstract class Dialog<T> {

    private String title;
    private String content;
    private RegexRule regExp;

    protected Dialog( RegexRule regExp) {
        this.title = "";
        this.regExp = regExp;
    }

    protected Dialog(String title,  RegexRule regExp) {
        this.title = title;
        this.regExp = regExp;
    }

    public  RegexRule regExp(){
        return this.regExp;
    }

    public T read() {
        String input;
        boolean valid;
        do {
            Console.instance().write(this.title + " (" + this.regExp.getDisplayName() + "): ");
            input = Console.instance().readString();
            valid = this.isValid(input);
            if (!valid) {
                Console.instance().writeln("Fallo!!!" + this.errorMsg());
            }
        } while (!valid);
        return this.create(input);
    }

    private String errorMsg() {
        String errorSemanticMsg = this.errorSemanticMsg();
        if (!errorSemanticMsg.isBlank()) {
            errorSemanticMsg = " o ";
        }
        return "Al no respetar el formato \"" + this.regExp.getDisplayName() + "\""
                + errorSemanticMsg;
    }

    protected String errorSemanticMsg() {
        return "";
    }

    private boolean isValid(String string) {
        assert string != null;
        boolean syntaxOk  = regExp.getPattern().asMatchPredicate().test(string);
        boolean semanticOk = this.isSemanticValid(string);
        return syntaxOk && semanticOk;
    }

    protected boolean isSemanticValid(String string) {
        return true;
    }

    public abstract T create(String input);

    public void write(T element) {
        assert this.title != null;

        Console.instance().write(element);
    }

    public void writeln(T element) {
        this.write(element);
        Console.instance().writeln();
    }

    public void writeDetails(T element) {
        assert this.title != null;
        assert element != null;

        this.content = "===============";
        this.addLine("toString: " + element.toString());
        this.addContent(element);
        Console.instance().writeln(this.content);
    }

    protected void addContent(T element) {

    }

    protected void addLine(String line) {
        this.content += "\n" + line;
    }

}
