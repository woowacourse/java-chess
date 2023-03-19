package chess.domain.piece;

import java.util.function.Function;

public enum Camp {
    WHITE(weight -> weight), BLACK(weight -> -weight), EMPTY(weight -> 0);

    private final Function<Integer, Integer> expression;

    Camp(final Function<Integer, Integer> expression) {
        this.expression = expression;
    }

    public int calculateDirection(final int weight) {
        return expression.apply(weight);
    }

    public static Camp nextTurn(final Camp turn) {
        if (turn.equals(WHITE)) {
            return BLACK;
        }
        if (turn.equals(BLACK)) {
            return WHITE;
        }
        return EMPTY;
    }
}
