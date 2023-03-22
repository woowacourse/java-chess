package chess.domain.piece;

import java.util.function.Function;

public enum Team {
    WHITE(weight -> weight),
    BLACK(weight -> -weight),
    EMPTY(weight -> 0),
    ;

    private final Function<Integer, Integer> expression;

    Team(Function<Integer, Integer> expression) {
        this.expression = expression;
    }

    public int calculateDirection(int weight) {
        return expression.apply(weight);
    }

    public Team nextTurn(Team turn) {
        if (turn.equals(WHITE)) {
            return BLACK;
        }
        if (turn.equals(BLACK)) {
            return WHITE;
        }
        return EMPTY;
    }
}
