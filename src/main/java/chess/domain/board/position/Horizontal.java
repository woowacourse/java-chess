package chess.domain.board.position;

import java.util.Arrays;

public enum Horizontal {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int index;

    Horizontal(final int index) {
        this.index = index;
    }

    public static Horizontal parse(final String symbol) {
        try {
            return Horizontal.valueOf(symbol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("체스판을 벗어난 세로열 위치입니다.");
        }
    }

    public static Horizontal of(final int index) {
        return Arrays.stream(Horizontal.values())
                .filter(v -> v.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판을 벗어난 세로열 위치입니다."));
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance(final Horizontal other) {
        return Math.abs(this.index - other.getIndex());
    }

    public Horizontal add(final int v) {
        return of(this.index + v);
    }
}
