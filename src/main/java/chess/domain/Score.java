package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.function.Predicate;

public enum Score {
    KING((piece) -> piece.matchType(PieceType.KING), 0),
    QUEEN((piece) -> piece.matchType(PieceType.QUEEN), 9),
    BISHOP((piece) -> piece.matchType(PieceType.BISHOP), 3),
    KNIGHT((piece) -> piece.matchType(PieceType.KNIGHT), 2.5),
    ROOK((piece) -> piece.matchType(PieceType.ROOK),5),
    PAWN((piece) -> piece.matchType(PieceType.PAWN),1),
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
