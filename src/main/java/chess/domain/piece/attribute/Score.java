package chess.domain.piece.attribute;

import chess.domain.piece.Article;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Predicate;

public enum Score {
    EMPTY_SCORE((piece) -> piece.getName().equals("."), 0),
    QUEEN_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("q"), 9),
    ROOK_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("r"), 5),
    BISHOP_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("b"), 3),
    KNIGHT_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("n"), 2.5),
    PAWN_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("p"), 1),
    KING_SCORE((piece) -> piece.getName().toLowerCase(Locale.ROOT).equals("k"), 0);

    private final Predicate<Article> piecePredicate;
    private final double value;

    Score(Predicate<Article> piecePredicate, double value) {
        this.piecePredicate = piecePredicate;
        this.value = value;
    }

    public static Score valueOf(Article piece) {
        return Arrays.stream(Score.values())
                .filter(score -> score.piecePredicate.test(piece))
                .findFirst()
                .orElseThrow();
    }

    public double getValue() {
        return value;
    }
}
