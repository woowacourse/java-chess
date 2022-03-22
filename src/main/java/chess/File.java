package chess;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    File(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
