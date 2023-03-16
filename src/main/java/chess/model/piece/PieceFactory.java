package chess.model.piece;

import chess.model.Color;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public final class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factories;

    static {
        factories = new EnumMap<>(PieceType.class);
        factories.put(PieceType.PAWN, PieceFactory::initialPawn);
        factories.put(PieceType.ROOK, Rook::new);
        factories.put(PieceType.KNIGHT, Knight::new);
        factories.put(PieceType.BISHOP, Bishop::new);
        factories.put(PieceType.KING, King::new);
        factories.put(PieceType.QUEEN, Queen::new);
    }

    private PieceFactory() {
    }

    private static Piece initialPawn(final Color color) {
        final Piece pawn = new Pawn(color);

        return new InitialPawn(pawn);
    }

    public static Piece create(final Color color, final PieceType type) {
        return factories.get(type).apply(color);
    }
}
