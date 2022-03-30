package chess.domain.piece.factory;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.common.Bishop;
import chess.domain.piece.common.Queen;
import chess.domain.piece.common.Rook;
import chess.domain.piece.ranged.King;
import chess.domain.piece.ranged.Knight;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factory = new HashMap<>();

    static {
        factory.put(PAWN, Pawn::new);
        factory.put(ROOK, Rook::new);
        factory.put(KNIGHT, Knight::new);
        factory.put(BISHOP, Bishop::new);
        factory.put(QUEEN, Queen::new);
        factory.put(KING, King::new);
    }

    public static Piece createPiece(final PieceType pieceType, final Color color) {
        return factory.get(pieceType).apply(color);
    }

    private PieceFactory() {
    }
}
