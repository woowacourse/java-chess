package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public enum Direction {
    N((start, destination) -> start.isOrthogonalWith(destination) && start.isBelow(destination), 0, -1),
    S((start, destination) -> start.isOrthogonalWith(destination) && start.isAbove(destination), 0, 1),
    E((start, destination) -> start.isOrthogonalWith(destination) && start.isFurtherLeftThan(destination), 1, 0),
    W((start, destination) -> start.isOrthogonalWith(destination) && start.isFurtherRightThan(destination), -1, 0),
    NE((start, destination) -> start.isDiagonalWith(destination) && start.isLeftLowerThan(destination), 1, -1),
    NW((start, destination) -> start.isDiagonalWith(destination) && start.isRightLowerThan(destination), -1, -1),
    SW((start, destination) -> start.isDiagonalWith(destination) && start.isRightUpperThan(destination), -1, 1),
    SE((start, destination) -> start.isDiagonalWith(destination) && start.isLeftUpperThan(destination), 1, 1);

    private final BiPredicate<Position, Position> matchCondition;
    private final int moveOnceFileWeight;
    private final int moveOnceRankWeight;

    Direction(BiPredicate<Position, Position> matchCondition, int moveOnceFileWeight,
              int moveOnceRankWeight) {
        this.matchCondition = matchCondition;
        this.moveOnceFileWeight = moveOnceFileWeight;
        this.moveOnceRankWeight = moveOnceRankWeight;
    }

    public static Direction of(Position start, Position destination) {
        return Arrays.stream(values())
                .filter(boardDirection -> boardDirection.matchCondition.test(start, destination))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("움직이는 방향을 찾는 것에 실패하였습니다"));
    }

    public int getMoveOnceFileWeight() {
        return moveOnceFileWeight;
    }

    public int getMoveOnceRankWeight() {
        return moveOnceRankWeight;
    }
}
