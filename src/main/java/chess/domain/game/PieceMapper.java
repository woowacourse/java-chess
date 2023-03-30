package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Side;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PieceMapper {

    private static final Map<String, BiFunction<Position, Side, Piece>> CACHE = new HashMap<>();

    static {
        CACHE.put("King", King::new);
        CACHE.put("Queen", Queen::new);
        CACHE.put("Bishop", Bishop::new);
        CACHE.put("Knight", Knight::new);
        CACHE.put("Rook", Rook::new);
        CACHE.put("Pawn", Pawn::new);
    }

    private PieceMapper() {
        throw new AssertionError();
    }

    public static Piece get(final String pieceType, final Position position, final Side side) {
        return CACHE.get(pieceType).apply(position, side);
    }
}
