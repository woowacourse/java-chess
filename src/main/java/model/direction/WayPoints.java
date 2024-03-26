package model.direction;

import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public record WayPoints(Direction direction, Map<Position, Piece> points) {

    public Map<Position, Piece> points() {
        return points;
    }

    public Direction direction() {
        return direction;
    }
}
