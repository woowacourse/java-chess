package chess.domain.route;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Route implements Movable {
    private final List<Position> route;

    public Route(List<Position> route) {
        this.route = route;
    }

    public static Route emptyRoute() {
        return new Route(new ArrayList<>());
    }

    public static Route findRoute(Position fromPosition, Direction direction, PieceType pieceType) {
        List<Position> movablePositions = findAllMovablePositions(fromPosition, direction);
        movablePositions = subListByPieceType(movablePositions, fromPosition, pieceType);

        if (pieceType == PieceType.PAWN) {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                return new RouteToProceed(movablePositions);
            }
            return new RouteToAttack(movablePositions);
        }

        return new Route(movablePositions);
    }

    private static List<Position> subListByPieceType(List<Position> movablePositions, Position fromPosition, PieceType pieceType) {
        if (pieceType == PieceType.PAWN) {
            if (fromPosition.isAt(Rank.TWO)) {
                movablePositions = movablePositions.subList(0, 2);
            }
            movablePositions = movablePositions.subList(0, 1);
        }

        if (pieceType == PieceType.KING) {
            movablePositions = movablePositions.subList(0, 1);
        }

        return movablePositions;
    }

    private static List<Position> findAllMovablePositions(Position fromPosition, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();
        Position movedPosition = fromPosition;

        while (true) {
            try {
                movedPosition = movedPosition.moveTo(direction);
                movablePositions.add(movedPosition);
            } catch (IllegalArgumentException e) {
                break;
            }
        }

        return movablePositions;
    }

    boolean contains(Position toPosition) {
        return route.contains(toPosition);
    }

    Route cutBefore(Position position) {
        List<Position> cutRoute = route.subList(0, route.indexOf(position));
        return new Route(cutRoute);
    }

    public List<Position> getRoute() {
        return Collections.unmodifiableList(route);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route1 = (Route) o;
        return Objects.equals(route, route1.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route);
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
}
