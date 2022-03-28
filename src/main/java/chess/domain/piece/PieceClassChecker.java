package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceClassChecker {
    KING((piece) -> piece.getClass().getSimpleName().equals("King")),
    QUEEN((piece) -> piece.getClass().getSimpleName().equals("Queen")),
    BISHOP((piece) -> piece.getClass().getSimpleName().equals("Bishop")),
    KNIGHT((piece -> piece.getClass().getSimpleName().equals("Knight"))),
    ROOK((piece) -> piece.getClass().getSimpleName().equals("Rook")),
    PAWN((piece) -> piece.getClass().getSimpleName().equals("Pawn")),
    INVALID((piece) -> piece.getClass().getSimpleName().equals("InvalidPiece")),
    ;

    private final Predicate<Piece> piecePredicate;

    PieceClassChecker(Predicate<Piece> piecePredicate) {
        this.piecePredicate = piecePredicate;
    }

    public static PieceClassChecker from(Piece piece) {
        return Arrays.stream(values())
            .filter(score -> score.piecePredicate.test(piece))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 기물입니다."));
    }
}
