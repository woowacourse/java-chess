package chess.domain.position;

public enum XAxis {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    XAxis(int value) {
        this.value = value;
    }

    int subtract(XAxis other) {
        return this.value - other.value;
    }
}
