package chess.domain.pieces;

import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PieceFactory {

    private static final Map<String, Piece> cache = new HashMap<>();

    static {
        cache.put("k", new King(Team.WHITE, Type.KING));
        cache.put("K", new King(Team.BLACK, Type.KING));
        cache.put("q", new Queen(Team.WHITE, Type.QUEEN));
        cache.put("Q", new Queen(Team.BLACK, Type.QUEEN));
        cache.put("b", new Bishop(Team.WHITE, Type.BISHOP));
        cache.put("B", new Bishop(Team.BLACK, Type.BISHOP));
        cache.put("n", new Knight(Team.WHITE, Type.KNIGHT));
        cache.put("N", new Knight(Team.BLACK, Type.KNIGHT));
        cache.put("r", new Rook(Team.WHITE, Type.ROOK));
        cache.put("R", new Rook(Team.BLACK, Type.ROOK));
        cache.put("p", new WhitePawn(Type.PAWN));
        cache.put("P", new BlackPawn(Type.PAWN));
        cache.put(".", new EmptyPiece(Team.NEUTRALITY, Type.NO_PIECE));
    }

    public static Piece of(String name) {
        return cache.get(name);
    }

    public static String from(Piece piece) {
        return cache.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), piece))
                .findAny()
                .get()
                .getKey();
    }
}
