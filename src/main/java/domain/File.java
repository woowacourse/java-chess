package domain;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
