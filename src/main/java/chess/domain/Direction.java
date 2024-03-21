package chess.domain;

import chess.dto.SquareDifferent;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Direction {

    LEFT((diff) -> diff.fileDiff() < 0 && diff.rankDiff() == 0),
    RIGHT((diff) -> diff.fileDiff() > 0 && diff.rankDiff() == 0),
    UP((diff) -> diff.fileDiff() == 0 && diff.rankDiff() > 0),
    DOWN((diff) -> diff.fileDiff() == 0 && diff.rankDiff() < 0),
    LEFT_UP((diff) -> diff.fileDiff() < 0 && diff.rankDiff() > 0),
    RIGHT_UP((diff) -> diff.fileDiff() > 0 && diff.rankDiff() > 0),
    LEFT_DOWN((diff) -> diff.fileDiff() < 0 && diff.rankDiff() < 0),
    RIGHT_DOWN((diff) -> diff.fileDiff() > 0 && diff.rankDiff() < 0),
    ;

    private final Predicate<SquareDifferent> decideDirection;

    Direction(Predicate<SquareDifferent> decideDirection) {
        this.decideDirection = decideDirection;
    }

    public static Direction findDirectionByDiff(SquareDifferent squareDifferent) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.decideDirection.test(squareDifferent))
                .findAny()
                .orElseThrow();
    }

    public boolean isDiagonal() {
        return this.equals(RIGHT_UP) || this.equals(LEFT_UP) || this.equals(RIGHT_DOWN) || this.equals(LEFT_DOWN);
    }
}
