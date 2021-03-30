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

    Vertical(final int index) {
        this.index = index;
    }

    public static Vertical parse(final String symbol) {
        try {
            return Vertical.valueOf(symbol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("체스판을 벗어난 세로열 위치입니다.");
        }
    }

    public static Vertical of(final int index) {
        return Arrays.stream(Vertical.values())
                .filter(v -> v.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판을 벗어난 세로열 위치입니다."));
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance(final Vertical other) {
        return Math.abs(this.index - other.getIndex());
    }

    public Vertical add(final int v) {
        return of(this.index + v);
    }
}
