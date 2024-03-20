package chess.board;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum Direction {

    POSITIVE_FILE_POSITIVE_RANK(
            (source, destination) -> source.hasLowerFileThan(destination) &&
                    source.isOnPositiveSlopeDiagonal(destination),
            position -> position.calculatePositionBy(1, 1)),
    NEGATIVE_FILE_NEGATIVE_RANK(
            (source, destination) -> source.hasHigherFileThan(destination) &&
                    source.isOnPositiveSlopeDiagonal(destination),
            position -> position.calculatePositionBy(-1, -1)),
    NEGATIVE_FILE_POSITIVE_RANK(
            (source, destination) -> source.hasHigherFileThan(destination) &&
                    source.isOnNegativeSlopeDiagonal(destination),
            position -> position.calculatePositionBy(-1, 1)),
    POSITIVE_FILE_NEGATIVE_RANK(
            (source, destination) -> source.hasLowerFileThan(destination) &&
                    source.isOnNegativeSlopeDiagonal(destination),
            position -> position.calculatePositionBy(1, -1)),
    SAME_FILE_POSITIVE_RANK(
            (source, destination) -> source.isOnSameFile(destination) &&
                    source.hasLowerRankThan(destination),
            position -> position.calculatePositionBy(0, 1)),
    SAME_FILE_NEGATIVE_RANK(
            (source, destination) -> source.isOnSameFile(destination) &&
                    source.hasHigherRankThan(destination),
            position -> position.calculatePositionBy(0, -1)),
    POSITIVE_FILE_SAME_RANK(
            (source, destination) -> source.isOnSameRank(destination) &&
                    source.hasLowerFileThan(destination),
            position -> position.calculatePositionBy(1, 0)),
    NEGATIVE_FILE_SAME_RANK(
            (source, destination) -> source.isOnSameRank(destination) &&
                    source.hasHigherFileThan(destination),
            position -> position.calculatePositionBy(-1, 0)),
    KNIGHT(Position::isOnKnightRoute, UnaryOperator.identity()),
    ;

    private final BiPredicate<Position, Position> predicate;
    private final UnaryOperator<Position> nextPositionGenerator;

    Direction(BiPredicate<Position, Position> predicate, UnaryOperator<Position> nextPositionGenerator) {
        this.predicate = predicate;
        this.nextPositionGenerator = nextPositionGenerator;
    }

    public static Direction calculateBetween(Position source, Position destination) {
        return Arrays.stream(values())
                .filter(direction -> direction.predicate.test(source, destination))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 방향입니다."));
    }

    public Position nextPosition(Position position) {
        return nextPositionGenerator.apply(position);
    }
}
