package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    Map<Point, Piece> points;

    public Board(Map<Point, Piece> points) {
        this.points = new HashMap<>(points);
    }

    public Piece get(Point point) {
        return points.get(point);
    }
}
