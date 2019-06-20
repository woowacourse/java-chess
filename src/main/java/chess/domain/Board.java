package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Point, Piece> pieces;

    public Board() {
        this.pieces = init();
    }

    private Map<Point, Piece> init() {
        Map<Point, Piece> pieces = new HashMap<>();
        List<Piece> white = PieceFactory.generateFirstPieces(true);
        List<Piece> black = PieceFactory.generateFirstPieces(false);
        for (int i = 0; i < 8; i++) {
            pieces.put(new Point((char) ('a' + i), '1'), white.get(i));
            pieces.put(new Point((char) ('a' + i), '2'), new Pawn(Team.WHITE));
            pieces.put(new Point((char) ('a' + i), '7'), new Pawn(Team.BLACK));
            pieces.put(new Point((char) ('a' + i), '8'), black.get(i));
        }
        return pieces;
    }

    public Map<Point, Piece> getPieces() {
        return pieces;
    }
}
