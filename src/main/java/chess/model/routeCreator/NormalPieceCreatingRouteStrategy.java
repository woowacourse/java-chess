package chess.model.routeCreator;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Direction;
import chess.model.board.vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class NormalPieceCreatingRouteStrategy implements CreatingRouteStrategy {
    private static final int MIN_MAGNITUDE = 1;

    @Override
    public Route create(List<Coordinate> coordinates, Vector vector) {
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
}
