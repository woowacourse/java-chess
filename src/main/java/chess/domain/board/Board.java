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

    public boolean canMove(Point prev, Point next) {
        DirectionType direction = DirectionType.valueOf(prev, next);
        int size = next.maxAbsoluteValue(prev);

        for (int i = 1; i < size - 1; i++) {
            Point moving = prev.moveOneStep(direction, i);
            if (points.get(moving).isPresent()) {
                return false;
            }
        }

        // TODO pawn
        Optional<Piece> nextPiece = points.get(next);
        if (nextPiece.isPresent() && points.get(prev).get().isSamePlayerType(nextPiece.get())) {
            return false;
        }
        return true;
    }

    public void move(Point prev, Point next) {
        if (canMove(prev, next) && points.get(prev).get().isMovable(prev, next)) {
            points.put(next, points.get(prev));
            points.put(prev, Optional.empty());
        }
    }
}
