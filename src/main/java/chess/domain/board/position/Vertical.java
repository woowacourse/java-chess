package chess.domain.board.position;

import chess.domain.piece.rule.Direction;
import chess.domain.piece.rule.Distance;

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
        return Vertical.valueOf(symbol.toUpperCase());
    }

    public static Vertical of(final int index) {
        return Arrays.stream(Vertical.values())
                .filter(v -> v.index == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public Vertical add(final Direction direction, final Distance distance) {
        return of(index + direction.getX() * distance.getValue());
    }
}
