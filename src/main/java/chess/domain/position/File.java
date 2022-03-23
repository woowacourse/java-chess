package chess.domain.position;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h")
    ;

    private final String file;

    File(String file) {
        this.file = file;
    }
}
