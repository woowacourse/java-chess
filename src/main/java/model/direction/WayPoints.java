package model.direction;

import model.piece.Piece;
import model.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public record WayPoints(Direction direction, Map<Position, Piece> points) {

    public Map<Position, Piece> points() {
        return points;
    }

    public Direction direction() {
        return direction;
    }

    public static WayPoints of(final Map<Position, Piece> chessBoard, final Route route, final Position target) {
        Map<Position, Piece> wayPoints = new LinkedHashMap<>();
        route.positions()
             .stream()
             .takeWhile(position -> !position.equals(target))
             .forEach(position -> wayPoints.put(position, chessBoard.get(position)));
        return new WayPoints(route.direction(), wayPoints);
    }
}
