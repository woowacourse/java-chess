package chess.domain.board;

public enum Column {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    Column(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
