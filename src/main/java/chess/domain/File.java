package chess.domain;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String file;
    private final int index;

    File(final String file, final int index) {
        this.file = file;
        this.index = index;
    }
}
