package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> PIECES = new HashMap<>();

    static {
        PIECES.put("p", new Pawn(Team.WHITE));
        PIECES.put("P", new Pawn(Team.BLACK));
        PIECES.put("q", new Queen(Team.WHITE));
        PIECES.put("Q", new Queen(Team.BLACK));
        PIECES.put("r", new Rook(Team.WHITE));
        PIECES.put("R", new Rook(Team.BLACK));
        PIECES.put("b", new Bishop(Team.WHITE));
        PIECES.put("B", new Bishop(Team.BLACK));
        PIECES.put("k", new King(Team.WHITE));
        PIECES.put("K", new King(Team.BLACK));
        PIECES.put("n", new Knight(Team.WHITE));
        PIECES.put("N", new Knight(Team.BLACK));
        PIECES.put(".", Blank.getInstance());
    }

    public static Piece correctPiece(final String name) {
        return PIECES.get(name);
    }

}
