package chess.domain.position;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int columnNumber;

    File(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int calculateDistanceWith(File other) {
        return Math.abs(columnNumber - other.columnNumber);
    }

    public boolean isFurtherLeftThan(File other) {
        return columnNumber < other.columnNumber;
    }

    public boolean isFurtherRightThan(File other) {
        return columnNumber > other.columnNumber;
    }
}
