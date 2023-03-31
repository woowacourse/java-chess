package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum PieceType {
    KING(King.class, King::new),
    QUEEN(Queen.class, Queen::new),
    BISHOP(Bishop.class, Bishop::new),
    KNIGHT(Knight.class, Knight::new),
    ROOK(Rook.class, Rook::new),
    PAWN(Pawn.class, Pawn::new);

    private static final Map<Class<? extends Piece>, PieceType> TYPE_BY_CLASS = new HashMap<>();

    static {
        for (PieceType pieceTyp : values()) {
            TYPE_BY_CLASS.put(pieceTyp.classType, pieceTyp);
        }
    }

    private final Class<? extends Piece> classType;
    private final BiFunction<Position, Color, Piece> constructor;

    PieceType(final Class<? extends Piece> classType, final BiFunction<Position, Color, Piece> constructor) {
        this.classType = classType;
        this.constructor = constructor;
    }

    public static PieceType findByPiece(final Piece piece) {
        return TYPE_BY_CLASS.get(piece.getClass());
    }

    public Piece getInstance(final Position position, final Color color) {
        return constructor.apply(position, color);
    }
}
