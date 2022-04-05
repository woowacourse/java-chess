package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.property.Color;

public class PieceFactory {
    private static final Map<String, Piece> FACTORY = new HashMap<>();

    static {
        FACTORY.put("r", new Pawn(Color.White));
        FACTORY.put("n", new Knight(Color.White));
        FACTORY.put("b", new Bishop(Color.White));
        FACTORY.put("q", new Queen(Color.White));
        FACTORY.put("k", new King(Color.White));
        FACTORY.put("p", new Pawn(Color.White));
        FACTORY.put("R", new Rook(Color.Black));
        FACTORY.put("N", new Knight(Color.Black));
        FACTORY.put("B", new Bishop(Color.Black));
        FACTORY.put("Q", new Queen(Color.Black));
        FACTORY.put("K", new King(Color.Black));
        FACTORY.put("P", new Pawn(Color.Black));
    }

    public static Piece createBy(String name) {
        return FACTORY.get(name);
    }
}
