package chess.domain;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.function.Predicate;

public enum Score {
    KING((piece) -> piece.getClass().getSimpleName().equals("King"), 0),
    QUEEN((piece) -> piece.getClass().getSimpleName().equals("Queen"), 9),
    BISHOP((piece) -> piece.getClass().getSimpleName().equals("Bishop"), 3),
    KNIGHT((piece -> piece.getClass().getSimpleName().equals("Knight")), 2.5),
    ROOK((piece) -> piece.getClass().getSimpleName().equals("Rook"), 5),
    PAWN((piece) -> piece.getClass().getSimpleName().equals("Pawn"), 1),
    ;

    private final Predicate<Piece> piecePredicate;
    private final double score;

    Score(Predicate<Piece> piecePredicate, double score) {
        this.piecePredicate = piecePredicate;
        this.score = score;
    }

    public static double from(Piece piece) {
        return Arrays.stream(values())
                .filter(score -> score.piecePredicate.test(piece))
                .mapToDouble(score -> score.score)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 기물입니다."));
    }
}
