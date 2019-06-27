package chess.model.routeCreator;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Vector;

import java.util.List;

@FunctionalInterface
public interface CreatingRouteStrategy {
    Route create(List<Coordinate> coordinates, Vector vector);
}
