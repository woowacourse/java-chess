package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {
    BISHOP(Type.BISHOP, Bishop::new),
    EMPTY(Type.EMPTY, Empty::new),
    KING(Type.KING, King::new),
    KNIGHT(Type.KNIGHT, Knight::new),
    PAWN(Type.PAWN, Pawn::new),
    QUEEN(Type.QUEEN, Queen::new),
    ROOK(Type.ROOK, Rook::new);

    private final Type type;
    private final BiFunction<Type, Side, Piece> function;

    PieceFactory(final Type type, final BiFunction<Type, Side, Piece> function) {
        this.type = type;
        this.function = function;
    }

    public static Piece createPiece(Type type, Side side) {
        final PieceFactory pieceFactory = Arrays.stream(values())
                .filter(value -> value.type == type)
                .findFirst()
                .orElseThrow();

        return pieceFactory.function.apply(type, side);
    }
}
