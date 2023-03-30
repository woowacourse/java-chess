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
        if (WHITE.equals(turn)) {
            return BLACK;
        }
        if (BLACK.equals(turn)) {
            return WHITE;
        }
        return EMPTY;
    }

    public static Team calculateWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
        if (blackScore > whiteScore) {
            return Team.BLACK;
        }
        return Team.EMPTY;
    }
}
