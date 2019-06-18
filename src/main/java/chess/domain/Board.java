package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Point, Piece> board;

    public Board() {
        board = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                board.put(new Point(i, j), new BlankPiece());
            }
        }
    }
    // b2 -> Point(2,2)

    public void set(Point point, Piece piece) {
        board.put(point, piece);
    }

    public void move(Point p1, Point p2) {

        List<Direction> directions = board.get(p1).range(p1, p2);
        for (int i = 0; i < directions.size(); i++) {
            if (!board.get(p1.transport(directions.get(i))).toString().equals(new BlankPiece().toString())) {
                throw new IllegalArgumentException();
            }
        }
        board.put(p2, board.get(p1));
        board.put(p1, new BlankPiece());
    }

    public Piece get(Point point) {
        return board.get(point);
    }
}
