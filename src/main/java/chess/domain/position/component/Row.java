package chess.domain.position.component;

public enum Row {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    Row(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
