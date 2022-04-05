package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> cache = new HashMap<>();

    static {
        cache.put("bishop_black", new Bishop(Team.BLACK));
        cache.put("rook_black", new Rook(Team.BLACK));
        cache.put("king_black", new King(Team.BLACK));
        cache.put("knight_black", new Knight(Team.BLACK));
        cache.put("pawn_black", new Pawn(Team.BLACK));
        cache.put("queen_black", new Queen(Team.BLACK));
        cache.put("bishop_white", new Bishop(Team.WHITE));
        cache.put("rook_white", new Rook(Team.WHITE));
        cache.put("king_white", new King(Team.WHITE));
        cache.put("knight_white", new Knight(Team.WHITE));
        cache.put("pawn_white", new Pawn(Team.WHITE));
        cache.put("queen_white", new Queen(Team.WHITE));
    }

    public static Piece of(String type, Team team) {
        return cache.get(madeKey(type, team));
    }

    private static String madeKey(String type, Team team) {
        return type.toLowerCase() + "_" + team.name().toLowerCase();
    }
}
