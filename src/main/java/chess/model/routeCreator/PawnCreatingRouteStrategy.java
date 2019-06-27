package chess.model.routeCreator;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Direction;
import chess.model.board.vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class PawnCreatingRouteStrategy implements CreatingRouteStrategy {
    private static final int MAX_MAGNITUDE_BY_ONE_TURN = 2;
    private static final int MIN_MAGNITUDE_BY_ONE_TURN = 1;

    private boolean isNotMoved;

    public PawnCreatingRouteStrategy(boolean isNotMoved) {
        this.isNotMoved = isNotMoved;
    }

    @Override
    public Route create(List<Coordinate> coordinates, Vector vector) {
        List<String> route = new ArrayList<>();

        addWhenMaxMagnitude(coordinates, vector, route);
        addWhenMinMagnitude(coordinates, vector, route);

        return new Route(route);
    }

    private void addWhenMaxMagnitude(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToMagnitude(MAX_MAGNITUDE_BY_ONE_TURN) && isNotMoved) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            Direction direction = vector.getDirection();

            route.add(coordinateX.addCoordinate(direction.getUnitX())
                    .concat(coordinateY.addCoordinate(MIN_MAGNITUDE_BY_ONE_TURN * direction.getUnitY())));
            route.add(coordinateX.addCoordinate(direction.getUnitX())
                    .concat(coordinateY.addCoordinate(MAX_MAGNITUDE_BY_ONE_TURN * direction.getUnitY())));
        }

        if (vector.isEqualToMagnitude(MAX_MAGNITUDE_BY_ONE_TURN) && !isNotMoved) {
            throw new IllegalArgumentException("한 턴에 2칸을 이동할 수 없습니다.");
        }
    }

    private void addWhenMinMagnitude(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToMagnitude(MIN_MAGNITUDE_BY_ONE_TURN)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            Direction direction = vector.getDirection();

            route.add(coordinateX.addCoordinate(direction.getUnitX())
                    .concat(coordinateY.addCoordinate(direction.getUnitY())));
        }
    }
}
