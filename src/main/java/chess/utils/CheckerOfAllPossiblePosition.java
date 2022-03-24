package chess.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CheckerOfAllPossiblePosition {

    public static boolean isMovableCoordinates(List<Pair<Integer, Integer>> coordinatesOfMovable,
                                               Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isMovableCoordinate(source, coordinate, target));
    }

    private static boolean isMovableCoordinate(Pair<Integer, Integer> source, Pair<Integer, Integer> coordinate, Pair<Integer, Integer> target) {
        return source.getRight() + coordinate.getRight() == target.getRight()
                && source.getLeft() + coordinate.getLeft() == target.getLeft();
    }
}


