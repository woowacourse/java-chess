package chess.domain.piece;

import java.util.Arrays;

public enum PieceScore {

    KING(King.class, 0),
    PAWN(Pawn.class, 1),
    KNIGHT(Knight.class, 2.5),
    BISHOP(Bishop.class, 3),
    ROOK(Rook.class, 5),
    QUEEN(Queen.class, 9);

    private final Class<? extends Piece> piece;
    private final double point;

    PieceScore(Class<? extends Piece> piece, double point) {
        this.piece = piece;
        this.point = point;
    }

    public static double findPieceScore(Class<? extends Piece> otherPiece) {
        return Arrays.stream(values())
            .filter(pieceScore -> pieceScore.piece.equals(otherPiece))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 유형의 기물이 존재하지 않습니다."))
            .point;
    }
}
