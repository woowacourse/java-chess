package chess.domain.position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Movement {
    private final Position source;
    private final Position target;

    public Movement(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public Set<Position> findRouteBetween() {
        Set<Position> route = new HashSet<>();
        Position nextPosition = source;
        for (final Direction direction : findDirections()) {
            nextPosition = nextPosition.move(direction);
            route.add(nextPosition);
        }
        route.remove(target);
        return route;
    }

    private List<Direction> findDirections() {
        int fileDiff = source.calculateFileDifferenceTo(target);
        int rankDiff = source.calculateRankDifferenceTo(target);
        if (isOnlyOneDirection(fileDiff, rankDiff)) {
            return findOnlyOneDirections(fileDiff, rankDiff);
        }
        return findLDirections(fileDiff, rankDiff);
    }

    private boolean isOnlyOneDirection(final int fileDiff, final int rankDiff) {
        return fileDiff == 0 || rankDiff == 0 || Math.abs(rankDiff) == Math.abs(fileDiff);
    }

    private List<Direction> findOnlyOneDirections(final int fileDiff, final int rankDiff) {
        return Stream.generate(() -> Direction.findDirection(fileDiff, rankDiff))
                .limit(Math.max(Math.abs(rankDiff), Math.abs(fileDiff)))
                .toList();
    }

    private List<Direction> findLDirections(final int fileDiff, final int rankDiff) {
        List<Direction> horizontalDirections = Stream.generate(() -> Direction.findDirection(fileDiff, 0)).limit(fileDiff).toList();
        List<Direction> verticalDirections = Stream.generate(() -> Direction.findDirection(0, rankDiff)).limit(rankDiff).toList();
        return union(horizontalDirections, verticalDirections);
    }

    private static List<Direction> union(final List<Direction> horizontalDirections, final List<Direction> verticalDirections) {
        List<Direction> directions = new ArrayList<>();
        if (horizontalDirections.size() > verticalDirections.size()) {
            directions.addAll(horizontalDirections);
            directions.addAll(verticalDirections);
            return directions;
        }
        directions.addAll(verticalDirections);
        directions.addAll(horizontalDirections);
        return directions;
    }

    public Direction findDirection() {
        int fileDiff = source.calculateFileDifferenceTo(target);
        int rankDiff = source.calculateRankDifferenceTo(target);
        return Direction.findDirection(fileDiff, rankDiff);
    }

    public int calculateFileDistance() {
        return Math.abs(source.calculateFileDifferenceTo(target));
    }

    public int calculateRankDistance() {
        return Math.abs(source.calculateRankDifferenceTo(target));
    }

    public int calculateDistance() {
        return source.calculateDistanceTo(target);
    }

    public boolean isSourceRank(final ChessRank rank) {
        return source.isRank(rank);
    }
}
