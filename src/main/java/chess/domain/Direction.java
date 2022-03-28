package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    LEFT(-1, 0,
        (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.isSameRank(p2)),
    RIGHT(1, 0,
        (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.isSameRank(p2)),
    UP(0, 1,
        (p1, p2) -> p1.isSameFile(p2) && p1.getRankDifference(p2) < 0),
    DOWN(0, -1,
        (p1, p2) -> p1.isSameFile(p2) && p1.getRankDifference(p2) > 0),
    LEFTUP(-1, 1,
        (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.getRankDifference(p2) < 0),
    RIGHTUP(1, 1,
        (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.getRankDifference(p2) < 0),
    LEFTDOWN(-1, -1,
        (p1, p2) -> p1.getFileDifference(p2) > 0 && p1.getRankDifference(p2) > 0),
    RIGHTDOWN(1, -1,
        (p1, p2) -> p1.getFileDifference(p2) < 0 && p1.getRankDifference(p2) > 0);

    private final int fileIncrement;
    private final int rankIncrement;
    private final BiPredicate<Position, Position> condition;

    Direction(int fileIncrement, int rankIncrement, BiPredicate<Position, Position> condition) {
        this.fileIncrement = fileIncrement;
        this.rankIncrement = rankIncrement;
        this.condition = condition;
    }

    public static Direction judge(Position p1, Position p2) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.condition.test(p1, p2))
            .findAny()
            .get();
    }

    public static Position getNextPosition(Position from, Direction direction) {
        return from.getIncreasedOneStepPosition(direction.fileIncrement, direction.rankIncrement);
    }
}
