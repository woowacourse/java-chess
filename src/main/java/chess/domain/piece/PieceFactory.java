package chess.domain.piece;

import chess.domain.piece.immediate.King;
import chess.domain.piece.immediate.Knight;
import chess.domain.piece.linear.Bishop;
import chess.domain.piece.linear.Queen;
import chess.domain.piece.linear.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<Type, Function<Side, Piece>> PIECE;

    static {
        PIECE = new HashMap<>();

        PIECE.put(Type.QUEEN, Queen::new);
        PIECE.put(Type.ROOK, Rook::new);
        PIECE.put(Type.BISHOP, Bishop::new);
        PIECE.put(Type.KNIGHT, Knight::new);
        PIECE.put(Type.PAWN, Pawn::new);
        PIECE.put(Type.KING, King::new);
        PIECE.put(Type.EMPTY, ignored -> new Empty());
    }

    private PieceFactory() {
    }

    public static Piece generatePiece(final Type type, final Side side) {
        final Function<Side, Piece> function = PIECE.get(type);
        return function.apply(side);
    }
}
