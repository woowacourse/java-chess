package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum Direction {

    POSITIVE_FILE_POSITIVE_RANK(
            (source, destination) -> source.hasLowerFileThan(destination) &&
                    source.isOnPositiveSlopeDiagonal(destination),
            position -> position.createPositionByDifferencesOf(1, 1)),
    NEGATIVE_FILE_NEGATIVE_RANK(
            (source, destination) -> source.hasHigherFileThan(destination) &&
                    source.isOnPositiveSlopeDiagonal(destination),
            position -> position.createPositionByDifferencesOf(-1, -1)),
    NEGATIVE_FILE_POSITIVE_RANK(
            (source, destination) -> source.hasHigherFileThan(destination) &&
                    source.isOnNegativeSlopeDiagonal(destination),
            position -> position.createPositionByDifferencesOf(-1, 1)),
    POSITIVE_FILE_NEGATIVE_RANK(
            (source, destination) -> source.hasLowerFileThan(destination) &&
                    source.isOnNegativeSlopeDiagonal(destination),
            position -> position.createPositionByDifferencesOf(1, -1)),
    SAME_FILE_POSITIVE_RANK(
            (source, destination) -> source.isOnSameFile(destination) &&
                    source.hasLowerRankThan(destination),
            position -> position.createPositionByDifferencesOf(0, 1)),
    SAME_FILE_NEGATIVE_RANK(
            (source, destination) -> source.isOnSameFile(destination) &&
                    source.hasHigherRankThan(destination),
            position -> position.createPositionByDifferencesOf(0, -1)),
    POSITIVE_FILE_SAME_RANK(
            (source, destination) -> source.isOnSameRank(destination) &&
                    source.hasLowerFileThan(destination),
            position -> position.createPositionByDifferencesOf(1, 0)),
    NEGATIVE_FILE_SAME_RANK(
            (source, destination) -> source.isOnSameRank(destination) &&
                    source.hasHigherFileThan(destination),
            position -> position.createPositionByDifferencesOf(-1, 0)),
    ;

    private final BiPredicate<Position, Position> checkDirection;
    private final UnaryOperator<Position> nextPositionGenerator;

    Direction(BiPredicate<Position, Position> checkDirection, UnaryOperator<Position> nextPositionGenerator) {
        this.checkDirection = checkDirection;
        this.nextPositionGenerator = nextPositionGenerator;
    }

    public static Direction calculateBetween(Position source, Position destination) {
        return Arrays.stream(values())
                .filter(direction -> direction.checkDirection.test(source, destination))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("이동할 수 없는 경로입니다."));
    }

    public Position nextPosition(Position position) {
        return nextPositionGenerator.apply(position);
    }
}
