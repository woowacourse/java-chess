package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.piece.strategy.vector.SlidingVector;
import java.util.ArrayList;
import java.util.List;

public class SlidingStrategy implements Strategy {

    private final List<SlidingVector> directions;

    public SlidingStrategy(final List<SlidingVector> directions) {
        this.directions = directions;
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final SlidingVector slidingVector = findDirectionVector(source, destination);
        return generateRoute(slidingVector, source, destination);
    }

    private SlidingVector findDirectionVector(final Square source, final Square destination) {
        final int distanceFile = destination.calculateDistanceFile(source);
        final int distanceRank = destination.calculateDistanceRank(source);
        return directions.stream()
                .filter(directions -> directions.isOnMyWay(distanceFile, distanceRank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 움직일 수 있는 경로가 아닙니다."));
    }

    private List<Square> generateRoute(final SlidingVector direction, final Square source, final Square destination) {
        final List<Square> route = new ArrayList<>();
        Square currentSquare = source;
        while (!currentSquare.equals(destination)) {
            currentSquare = currentSquare.next(direction);
            route.add(currentSquare);
        }
        return route;
    }
}
