package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {

    KING(King.class, 0),
    QUEEN(Queen.class, 9),
    ROOK(Rook.class, 5),
    BISHOP(Bishop.class, 3),
    KNIGHT(Knight.class, 2.5),
    PAWN(Pawn.class, 1);

    private final Class<? extends Piece> category;
    private final double score;

    PieceType(Class<? extends Piece> category, double score) {
        this.category = category;
        this.score = score;
    }

    public static PieceType from(Piece piece) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.category == piece.getClass())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 존재하지 않습니다."));
    }

    public static double scoreOf(Piece piece) {
        return from(piece).score;
    }
}
