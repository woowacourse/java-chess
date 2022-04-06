package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> cache = new HashMap<>();

    static {
        cache.put("WHITE-K", new King(Symbol.KING, Team.WHITE));
        cache.put("BLACK-K", new King(Symbol.KING, Team.BLACK));
        cache.put("WHITE-Q", new Queen(Symbol.QUEEN, Team.WHITE));
        cache.put("BLACK-Q", new Queen(Symbol.QUEEN, Team.BLACK));
        cache.put("WHITE-B", new Bishop(Symbol.BISHOP, Team.WHITE));
        cache.put("BLACK-B", new Bishop(Symbol.BISHOP, Team.BLACK));
        cache.put("WHITE-N", new Knight(Symbol.KNIGHT, Team.WHITE));
        cache.put("BLACK-N", new Knight(Symbol.KNIGHT, Team.BLACK));
        cache.put("WHITE-R", new Rook(Symbol.ROOK, Team.WHITE));
        cache.put("BLACK-R", new Rook(Symbol.ROOK, Team.BLACK));
        cache.put("WHITE-P", new Pawn(Symbol.PAWN, Team.WHITE));
        cache.put("BLACK-P", new Pawn(Symbol.PAWN, Team.BLACK));
        cache.put("NONE-.", new Empty(Symbol.EMPTY, Team.NONE));
    }

    public static Piece of(String team, String symbol) {
        return cache.get(team.toUpperCase() + "-" + symbol.toUpperCase());
    }
}
