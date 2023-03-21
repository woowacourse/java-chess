package chess.model.piece;

import chess.model.Color;
import chess.model.piece.type.Bishop;
import chess.model.piece.type.InitialPawn;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Pawn;
import chess.model.piece.type.Piece;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;
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
        if (cannotFoundPiece(type)) {
            throw new IllegalStateException("존재하지 않는 기물입니다.");
        }
        return factories.get(type).apply(color);
    }

    private static boolean cannotFoundPiece(final PieceType type) {
        return !factories.containsKey(type);
    }
}
