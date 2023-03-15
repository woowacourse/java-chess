package chess.model.piece;

import chess.model.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factories;

    static {
        factories = new HashMap<>();
        factories.put(PieceType.PAWN, Pawn::new);
        factories.put(PieceType.ROOK, Rook::new);
        factories.put(PieceType.KNIGHT, Knight::new);
        factories.put(PieceType.BISHOP, Bishop::new);
        factories.put(PieceType.KING, King::new);
        factories.put(PieceType.QUEEN, Queen::new);
    }

    private PieceFactory() {
    }

    public static Piece create(final Color color, final PieceType type) {
        return factories.get(type).apply(color);
    }
}
