package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;


public enum Direction {

    LEFT(-1, 0, (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.isSameRank(p2)),
    RIGHT(1, 0, (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.isSameRank(p2)),
    UP(0, 1, (p1, p2) -> p1.isSameFile(p2) && p1.getRankDifference(p2) < 0),
    DOWN(0, -1, (p1, p2) -> p1.isSameFile(p2) && p1.getRankDifference(p2) > 0),
    LEFTUP(-1, 1, (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.getRankDifference(p2) < 0),
    RIGHTUP(1, 1, (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.getRankDifference(p2) < 0),
    LEFTDOWN(-1, -1,
        (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.getRankDifference(p2) > 0),
    RIGHTDOWN(1, -1,
        (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.getRankDifference(p2) > 0);

    private static final String NON_MOVABLE_DIRECTION = "[ERROR] 지정한 목적지는 갈 수 있는 방향이 존재하지 않습니다.";

    private final int xPoint;
    private final int yPoint;
    private BiPredicate<Position, Position> condition;

    Direction(int xPoint, int yPoint,
        BiPredicate<Position, Position> condition) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.condition = condition;
    }

    public static Direction judge(Position p1, Position p2) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.condition.test(p1, p2))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NON_MOVABLE_DIRECTION));
    }

    public static Position step(Position from, Direction direction) {
        int xDifference = direction.xPoint;
        int yDifference = direction.yPoint;
        return from.getIncreasedOneStepPosition(xDifference, yDifference);
    }
}
