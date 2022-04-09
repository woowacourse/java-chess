package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Symbol {
    ROOK_BLACK("rb", 5.0),
    KNIGHT_BLACK("nb", 2.5),
    BISHOP_BLACK("bb", 3.0),
    QUEEN_BLACK("qb", 9.0),
    KING_BLACK("kb", 0.0),
    PAWN_BLACK("pb", 1.0),
    ROOK_WHITE("rb", 5.0),
    KNIGHT_WHITE("nb", 2.5),
    BISHOP_WHITE("bb", 3.0),
    QUEEN_WHITE("qb", 9.0),
    KING_WHITE("kb", 0.0),
    PAWN_WHITE("pb", 1.0);

    private final String viewValue;
    private final double score;

    Symbol(String viewValue, double score) {
        this.viewValue = viewValue;
        this.score = score;
    }

    public static double getScore(Symbol symbol) {
        final Symbol found = Arrays.stream(values())
                .filter(value -> value == symbol)
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return found.score;
    }

    public static String getName(Symbol symbol) {
        final Symbol found = Arrays.stream(values())
                .filter(value -> value == symbol)
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return found.viewValue;
    }
}
