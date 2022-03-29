package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceClassChecker {
    KING((piece) -> checkPieceClass(piece, King.class.getName())),
    QUEEN((piece) -> checkPieceClass(piece, Queen.class.getName())),
    BISHOP((piece) -> checkPieceClass(piece, Bishop.class.getName())),
    KNIGHT((piece) -> checkPieceClass(piece, Knight.class.getName())),
    ROOK((piece) -> checkPieceClass(piece, Rook.class.getName())),
    PAWN((piece) -> checkPieceClass(piece, Pawn.class.getName())),
    INVALID((piece) -> checkPieceClass(piece, InvalidPiece.class.getName())),
    ;

    private static boolean checkPieceClass(Piece piece, String targetPiece) {
        return piece.getClass().getName().equals(targetPiece);
    }

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
