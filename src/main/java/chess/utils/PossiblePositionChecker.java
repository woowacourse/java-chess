package chess.utils;

import chess.position.Direction;
import chess.position.Position;

import java.util.List;

public class PossiblePositionChecker {

    private PossiblePositionChecker() {
    }

    public static boolean isMovableCoordinates(List<Direction> coordinatesOfMovable, Position source, Position target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isMovableCoordinate(source, coordinate, target));
    }

    private static boolean isMovableCoordinate(Position source, Direction direction, Position target) {
        return source.findPossiblePosition(direction).isSameRow(target)
                && source.findPossiblePosition(direction).isSameColumn(target);
    }
}


