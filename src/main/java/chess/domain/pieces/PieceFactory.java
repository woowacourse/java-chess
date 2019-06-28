package chess.domain.pieces;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static Map<String, Piece> bucket = new HashMap<>();

    static {
        bucket.put("nB", new Blank(Color.NONE));
        bucket.put("wK", new King(Color.WHITE));
        bucket.put("wN", new Knight(Color.WHITE));
        bucket.put("wB", new Bishop(Color.WHITE));
        bucket.put("wQ", new Queen(Color.WHITE));
        bucket.put("wP", new Pawn(Color.WHITE));
        bucket.put("wR", new Rook(Color.WHITE));
        bucket.put("bK", new King(Color.BLACK));
        bucket.put("bN", new Knight(Color.BLACK));
        bucket.put("bB", new Bishop(Color.BLACK));
        bucket.put("bQ", new Queen(Color.BLACK));
        bucket.put("bP", new Pawn(Color.BLACK));
        bucket.put("bR", new Rook(Color.BLACK));
    }

    public static Piece of(String colorAndType) {
        return bucket.get(colorAndType);
    }
}
