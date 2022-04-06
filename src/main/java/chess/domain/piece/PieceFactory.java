package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    public static final Map<String, AbstractPiece> pieceFactory = new HashMap<>();

    static {
        pieceFactory.put("BLACKPAWN", new Pawn(PieceColor.BLACK));
        pieceFactory.put("WHITEPAWN", new Pawn(PieceColor.WHITE));
        pieceFactory.put("BLACKROOK", new Rook(PieceColor.BLACK));
        pieceFactory.put("WHITEROOK", new Rook(PieceColor.WHITE));
        pieceFactory.put("BLACKKNIGHT", new Knight(PieceColor.BLACK));
        pieceFactory.put("WHITEKNIGHT", new Knight(PieceColor.WHITE));
        pieceFactory.put("BLACKKING", new King(PieceColor.BLACK));
        pieceFactory.put("WHITEKING", new King(PieceColor.WHITE));
        pieceFactory.put("BLACKQUEEN", new Queen(PieceColor.BLACK));
        pieceFactory.put("WHITEQUEEN", new Queen(PieceColor.WHITE));
    }

    public static AbstractPiece getPiece(String pieceName) {
        return pieceFactory.get(pieceName);
    }
}
