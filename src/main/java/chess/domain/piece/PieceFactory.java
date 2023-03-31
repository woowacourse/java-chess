package chess.domain.piece;

import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factory = init();

    private static Map<PieceType, Function<Color, Piece>> init() {
        final EnumMap<PieceType, Function<Color, Piece>> factory = new EnumMap<>(PieceType.class);
        factory.put(PieceType.KING, King::new);
        factory.put(PieceType.QUEEN, Queen::new);
        factory.put(PieceType.ROOK, Rook::new);
        factory.put(PieceType.BISHOP, Bishop::new);
        factory.put(PieceType.KNIGHT, Knight::new);
        factory.put(PieceType.PAWN, PieceFactory::createPawn);
        return factory;
    }

    private static Piece createPawn(final Color color) {
        if (Color.WHITE == color) {
            return new WhitePawn();
        }
        return new BlackPawn();
    }

    public static Piece create(final PieceType pieceType, final Color color) {
        return factory.get(pieceType).apply(color);
    }
}
