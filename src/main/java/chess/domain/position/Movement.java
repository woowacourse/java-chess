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
        for (final ChessDirection chessDirection : findDirections()) {
            nextPosition = nextPosition.move(chessDirection);
            route.add(nextPosition);
        }
        route.remove(target);
        return route;
    }

    private List<ChessDirection> findDirections() {
        int fileDiff = source.calculateFileDifferenceTo(target);
        int rankDiff = source.calculateRankDifferenceTo(target);
        if(findDirection().isLShaped()){
            return findLShapedDirections(fileDiff, rankDiff);
        }
        return Stream.generate(() -> ChessDirection.findDirection(fileDiff, rankDiff))
                .limit(Math.max(Math.abs(rankDiff), Math.abs(fileDiff)))
                .toList();
    }

    private List<ChessDirection> findLShapedDirections(final int fileDiff, final int rankDiff) {
        List<ChessDirection> horizontalDirections = Stream.generate(() -> ChessDirection.findDirection(fileDiff, 0)).limit(fileDiff).toList();
        List<ChessDirection> verticalDirections = Stream.generate(() -> ChessDirection.findDirection(0, rankDiff)).limit(rankDiff).toList();
        return union(horizontalDirections, verticalDirections);
    }

    private static List<ChessDirection> union(final List<ChessDirection> horizontalDirections, final List<ChessDirection> verticalDirections) {
        List<ChessDirection> directions = new ArrayList<>();
        if (horizontalDirections.size() > verticalDirections.size()) {
            directions.addAll(horizontalDirections);
            directions.addAll(verticalDirections);
            return directions;
        }
        directions.addAll(verticalDirections);
        directions.addAll(horizontalDirections);
        return directions;
    }

    public ChessDirection findDirection() {
        int fileDiff = source.calculateFileDifferenceTo(target);
        int rankDiff = source.calculateRankDifferenceTo(target);
        return ChessDirection.findDirection(fileDiff, rankDiff);
    }

    public int calculateDistance() {
        return source.calculateDistanceTo(target);
    }

    public boolean isSourceRank(final ChessRank rank) {
        return source.isRank(rank);
    }
}
