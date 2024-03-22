package chess.position;

import java.util.Arrays;

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
    KNIGHT(Position::isOnKnightRoute, position -> position),
    ;

    private final DirectionMatcher matcher;
    private final NextPositionGenerator nextPositionGenerator;

    Direction(DirectionMatcher matcher, NextPositionGenerator nextPositionGenerator) {
        this.matcher = matcher;
        this.nextPositionGenerator = nextPositionGenerator;
    }

    public static Direction calculateBetween(Position source, Position destination) {
        return Arrays.stream(values())
                .filter(direction -> direction.matcher.matches(source, destination))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 방향입니다."));
    }

    public Position nextPosition(Position position) {
        return nextPositionGenerator.generate(position);
    }
}
