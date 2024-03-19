package domain.piece;

public enum ChessFile {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String value;
    private final int index;

    ChessFile(String value, int index) {
        this.value = value;
        this.index = index;
    }
}
