package chess.domain;

import chess.domain.square.Square;
import chess.domain.square.dto.SquareDifferent;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Direction {

    LEFT((diff) -> diff.fileDiff() < 0 && diff.rankDiff() == 0, -1, 0),
    RIGHT((diff) -> diff.fileDiff() > 0 && diff.rankDiff() == 0, 1, 0),
    UP((diff) -> diff.fileDiff() == 0 && diff.rankDiff() > 0, 0, 1),
    DOWN((diff) -> diff.fileDiff() == 0 && diff.rankDiff() < 0, 0, -1),
    LEFT_UP((diff) -> diff.fileDiff() < 0 && diff.rankDiff() > 0, -1, 1),
    RIGHT_UP((diff) -> diff.fileDiff() > 0 && diff.rankDiff() > 0, 1, 1),
    LEFT_DOWN((diff) -> diff.fileDiff() < 0 && diff.rankDiff() < 0, -1, -1),
    RIGHT_DOWN((diff) -> diff.fileDiff() > 0 && diff.rankDiff() < 0, 1, -1),
    ;

    private final Predicate<SquareDifferent> directionCondition;
    private final int fileIndex;
    private final int rankIndex;

    Direction(Predicate<SquareDifferent> directionCondition, int fileIndex, int rankIndex) {
        this.directionCondition = directionCondition;
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
    }

    public static Direction findDirectionByDiff(SquareDifferent squareDifferent) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.directionCondition.test(squareDifferent))
                .findAny()
                .orElseThrow();
    }

    public boolean isVertical() {
        return this.equals(UP) || this.equals(DOWN);
    }

    public boolean isHorizontal() {
        return this.equals(LEFT) || this.equals(RIGHT);
    }

    public boolean isDiagonal() {
        return this.equals(RIGHT_UP) || this.equals(LEFT_UP) || this.equals(RIGHT_DOWN) || this.equals(LEFT_DOWN);
    }

    public Square nextSquare(Square square) {
        if (isVertical()) {
            return square.moveVertical(rankIndex);
        }

        if (isHorizontal()) {
            return square.moveHorizontal(fileIndex);
        }

        return square.moveDiagonal(fileIndex, rankIndex);
    }
}
