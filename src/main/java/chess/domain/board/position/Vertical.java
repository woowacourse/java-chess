package chess.domain.board.position;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.Arrays;
import java.util.Locale;

public enum Vertical {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private final int index;

    Vertical(final int index) {
        this.index = index;
    }

    public static Vertical parse(final String symbol) {
        return Vertical.valueOf(symbol.toLowerCase(Locale.ROOT));
    }

    public static Vertical of(final int index) {
        return Arrays.stream(Vertical.values())
                .filter(v -> v.index == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Vertical add(final Direction direction, final Distance distance) {
        return of(index + direction.getX() * distance.getValue());
    }

    public int getIndex() {
        return index;
    }
}
