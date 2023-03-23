package chess.model.piece.type;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class PieceFactory {

    private static final Map<Class<? extends Piece>, Function<Camp, Piece>> factories = new HashMap<>();

    static {
        factories.put(Bishop.class, Bishop::new);
        factories.put(Pawn.class, PieceFactory::initialPawn);
        factories.put(King.class, King::new);
        factories.put(Knight.class, Knight::new);
        factories.put(Queen.class, Queen::new);
        factories.put(Rook.class, Rook::new);
    }

    private PieceFactory() {
    }

    private static Piece initialPawn(final Camp camp) {
        final Piece pawn = new Pawn(camp);

        return new InitialPawn(pawn);
    }

    public static Piece create(final Class<? extends Piece> type, Camp camp) {
        if (cannotFoundPiece(type)) {
            throw new IllegalStateException("존재하지 않는 기물입니다.");
        }
        return factories.get(type).apply(camp);
    }

    private static boolean cannotFoundPiece(final Class<? extends Piece> type) {
        return !factories.containsKey(type);
    }
}
