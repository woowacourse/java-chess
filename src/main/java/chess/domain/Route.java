package chess.domain;

import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private final List<Position> route;

    public Route(List<Position> route) {
        this.route = route;
    }

    public static Route emptyRoute() {
        return new Route(new ArrayList<>());
    }

    public static Route findEveryRouteToOneDirection(Position fromPosition, Position toPosition, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();

        Position movedPosition = fromPosition;
        while (true) {
            try {
                movedPosition = movedPosition.moveTo(direction);
                movablePositions.add(movedPosition);
                if (movedPosition.equals(toPosition)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                break;
            }
        }

        return new Route(movablePositions);
    }

    public static Route findOneRoute(Position fromPosition, Position toPosition, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();

        Position movedPosition = fromPosition;
        try {
            movedPosition = movedPosition.moveTo(direction);
            movablePositions.add(movedPosition);
        } catch (IllegalArgumentException e) {
            return Route.emptyRoute();
        }

        return new Route(movablePositions);
    }

    public static Route findPawnRoute(Position fromPosition, Position toPosition, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();

        Position movedPosition = fromPosition;
        try {
            movedPosition = movedPosition.moveTo(direction);
            movablePositions.add(movedPosition);

            if (movedPosition.equals(toPosition)) {
                return new Route(movablePositions);
            }

            if (fromPosition.isAt(Rank.TWO)) {
                movedPosition = movedPosition.moveTo(direction);
                movablePositions.add(movedPosition);
            }
        } catch (IllegalArgumentException e) {
            return Route.emptyRoute();
        }

        return new Route(movablePositions);
    }

    public boolean contains(Position toPosition) {
        return route.contains(toPosition);
    }

    public List<Position> getRoute() {
        return Collections.unmodifiableList(route);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Position position : route) {
            stringBuilder.append(position);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public void remove(Position position) {
        route.remove(position);
    }
}
