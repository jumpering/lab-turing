package main.es.labturing.utils.views;

public enum Language {

    SPANISH("es"),
    ENGLISH("en");

    private String fileName;

    private Language(String fileName) {
        this.fileName = "messages_"+fileName+".properties";
    }

    public String getFileName() {
        return this.fileName;
    }
}
