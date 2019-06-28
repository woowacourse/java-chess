package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.PieceValue;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private static final Map<PieceValue, CreatePiece> CREATE_MAP = new HashMap<>();

    static {
        CREATE_MAP.put(PieceValue.KING, King::new);
        CREATE_MAP.put(PieceValue.QUEEN, Queen::new);
        CREATE_MAP.put(PieceValue.ROOK, Rook::new);
        CREATE_MAP.put(PieceValue.KNIGHT, Knight::new);
        CREATE_MAP.put(PieceValue.BISHOP, Bishop::new);
        CREATE_MAP.put(PieceValue.PAWN, Pawn::new);
    }

    public static Piece createPiece(Aliance aliance, PieceValue pieceValue) {
        return CREATE_MAP.get(pieceValue).create(aliance, pieceValue);
    }
}

interface CreatePiece {
    Piece create(Aliance aliance, PieceValue pieceValue);
}

