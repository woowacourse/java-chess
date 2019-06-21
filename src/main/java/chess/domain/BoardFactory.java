package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {
    public static Map<Point, Piece> init() {
        Map<Point, Piece> pieces = new HashMap<>();
        List<Piece> white = PieceFactory.generateFirstPieces(Team.WHITE);
        List<Piece> black = PieceFactory.generateFirstPieces(Team.BLACK);
        for (int i = 0; i < 8; i++) {
            pieces.put(new Point((char) ('a' + i), '1'), white.get(i));
            pieces.put(new Point((char) ('a' + i), '2'), new Pawn(Team.WHITE));
            pieces.put(new Point((char) ('a' + i), '7'), new Pawn(Team.BLACK));
            pieces.put(new Point((char) ('a' + i), '8'), black.get(i));
        }
        return pieces;
    }
}
