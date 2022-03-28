package chess.utils;

import chess.position.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CheckerOfAllPossiblePosition {

    public static boolean isMovableCoordinates(List<Pair<Integer, Integer>> coordinatesOfMovable, Position source, Position target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isMovableCoordinate(source, coordinate, target));
    }

    private static boolean isMovableCoordinate(Position source, Pair<Integer, Integer> coordinate, Position target) {
        return source.findPossiblePosition(coordinate.getLeft(), 0).isSameRow(target)
                && source.findPossiblePosition(0, coordinate.getRight()).isSameColumn(target);
    }
}


