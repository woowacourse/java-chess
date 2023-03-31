package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceTypeFormatter {

    private static final Map<Class<? extends Piece>, String> typeByPiece = new HashMap<>();

    static {
        typeByPiece.put(King.class, "KING");
        typeByPiece.put(Queen.class, "QUEEN");
        typeByPiece.put(Bishop.class, "BISHOP");
        typeByPiece.put(Knight.class, "KNIGHT");
        typeByPiece.put(Rook.class, "ROOK");
        typeByPiece.put(Pawn.class, "PAWN");
    }

    private final String pieceType;

    public PieceTypeFormatter(Piece piece) {
        this.pieceType = typeByPiece.get(piece.getClass());
    }

    public String getPieceType() {
        return pieceType;
    }
}
