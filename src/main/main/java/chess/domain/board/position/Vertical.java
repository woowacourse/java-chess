package chess.domain.board.position;

import java.util.Arrays;

public enum Vertical {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int index;

    Vertical(int index) {
        this.index = index;
    }

    static Vertical indexOf(String symbol) {
        return Vertical.valueOf(symbol.toUpperCase());
    }

    static Vertical of(int index) {
        return Arrays.stream(Vertical.values())
                .filter(v -> v.index == index)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public int getDistance(Vertical other) {
        return Math.abs(this.index - other.getIndex());
    }

    public Vertical add(int v) {
        return of(index + v);
    }
}
