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

    private final String value;
    private final int index;

    File(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public int calculateDistance(final File otherFile) {
        return Math.abs(this.index - otherFile.index);
    }
}
