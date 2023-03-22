package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public enum PieceType {
    KING(King.class),
    QUEEN(Queen.class),
    BISHOP(Bishop.class),
    KNIGHT(Knight.class),
    ROOK(Rook.class),
    PAWN(Pawn.class);

    private static final Map<Class<? extends Piece>, PieceType> TYPE_BY_CLASS = new HashMap<>();

    static {
        for (PieceType pieceTyp : values()) {
            TYPE_BY_CLASS.put(pieceTyp.classType, pieceTyp);
        }
    }

    private final Class<? extends Piece> classType;

    PieceType(final Class<? extends Piece> classType) {
        this.classType = classType;
    }

    public static PieceType findByPiece(final Piece piece) {
        return TYPE_BY_CLASS.get(piece.getClass());
    }
}
