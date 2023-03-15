package chess.domain;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int position;

    File(final int position) {
        this.position = position;
    }

    public int calculateDifference(final File target) {
        return this.position - target.position;
    }

}
