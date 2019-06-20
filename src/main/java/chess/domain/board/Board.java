package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    Map<Point, Optional<Piece>> points;

    public Board(Map<Point, Optional<Piece>> points) {
        this.points = new HashMap<>(points);
    }

    public Optional<Piece> get(Point point) {
        return points.get(point);
    }
}
