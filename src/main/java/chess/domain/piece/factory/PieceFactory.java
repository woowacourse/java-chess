package chess.domain.piece.factory;

import static chess.domain.PieceType.BISHOP;
import static chess.domain.PieceType.KING;
import static chess.domain.PieceType.KNIGHT;
import static chess.domain.PieceType.PAWN;
import static chess.domain.PieceType.QUEEN;
import static chess.domain.PieceType.ROOK;

import chess.domain.PieceType;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
