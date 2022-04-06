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
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factory =
            Map.of(PAWN, Pawn::new, ROOK, Rook::new, KNIGHT, Knight::new,
                    BISHOP, Bishop::new, QUEEN, Queen::new, KING, King::new);

    private PieceFactory() {
    }

    public static Piece createPiece(PieceType pieceType, Color color) {
        return factory.get(pieceType).apply(color);
    }
}
