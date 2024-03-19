package domain;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public int gap(final File other) {
        return Math.abs(this.index - other.index);
    }
}
