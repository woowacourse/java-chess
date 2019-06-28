package model.piece;

import model.Position;
import model.piece.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PieceFactory {
    private static Map<Class<?>, BiFunction<PieceColor, Position, Piece>> pieceCreators;

    static {
        pieceCreators = new HashMap<>();
        pieceCreators.put(King.class, King::new);
        pieceCreators.put(Queen.class, Queen::new);
        pieceCreators.put(Rook.class, Rook::new);
        pieceCreators.put(Bishop.class, Bishop::new);
        pieceCreators.put(Knight.class, Knight::new);
        pieceCreators.put(Pawn.class, Pawn::new);
    }

    private PieceFactory() {
    }

    public static Piece create(Class<?> peiceType, PieceColor pieceColor, Position position) {
        return pieceCreators.getOrDefault(peiceType, Empty::new).apply(pieceColor, position);
    }

    public static Piece empty(Position position) {
        return new Empty(position);
    }
}
