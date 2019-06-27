package chess.model.routeCreator;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Direction;
import chess.model.board.vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class RouteCreator {
    private static final int MIN_MAGNITUDE = 1;
    private static final int MAX_MAGNITUDE_BY_ONE_TURN = 2;
    private static final int MIN_MAGNITUDE_BY_ONE_TURN = 1;

    public static Route createByNormalPiece(List<Coordinate> coordinates, Vector vector) {
        List<String> routes = new ArrayList<>();
        Coordinate coordinateX = coordinates.get(0);
        Coordinate coordinateY = coordinates.get(1);
        Direction direction = vector.getDirection();

        for (int i = MIN_MAGNITUDE; i <= vector.getMagnitude(); i++) {
            routes.add(coordinateX.addCoordinate(direction.getUnitX() * i)
                    .concat(coordinateY.addCoordinate(direction.getUnitY() * i)));
        }

        return new Route(routes);
    }

    public static Route createByKnight(List<Coordinate> coordinates, Vector vector) {
        List<String> routes = new ArrayList<>();
        Coordinate coordinateX = coordinates.get(0);
        Coordinate coordinateY = coordinates.get(1);
        Direction direction = vector.getDirection();

        routes.add(coordinateX.addCoordinate(direction.getUnitX())
                .concat(coordinateY.addCoordinate(direction.getUnitY())));

        return new Route(routes);
    }

    public static Route createByPawn(List<Coordinate> coordinates, Vector vector, boolean isNotMoved) {
        if (vector.isEqualToMagnitude(MAX_MAGNITUDE_BY_ONE_TURN)) {
            return addWhenMaxMagnitude(coordinates, vector, isNotMoved);
        }
        if (vector.isEqualToMagnitude(MIN_MAGNITUDE_BY_ONE_TURN)) {
            return addWhenMinMagnitude(coordinates, vector);
        }

        throw new IllegalArgumentException("폰은 1칸 혹은 2칸만 움직일 수 있습니다.");
    }

    private static Route addWhenMaxMagnitude(List<Coordinate> coordinates, Vector vector, boolean isNotMoved) {
        List<String> route = new ArrayList<>();

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

        return new Route(route);
    }

    private static Route addWhenMinMagnitude(List<Coordinate> coordinates, Vector vector) {
        List<String> route = new ArrayList<>();

        if (vector.isEqualToMagnitude(MIN_MAGNITUDE_BY_ONE_TURN)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            Direction direction = vector.getDirection();

            route.add(coordinateX.addCoordinate(direction.getUnitX())
                    .concat(coordinateY.addCoordinate(direction.getUnitY())));
        }

        return new Route(route);
    }
}
