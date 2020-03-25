package chess.domain.position;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum MoveDirection {
    N((fileInterval, rankInterval) -> fileInterval == 0 && rankInterval > 0,
            (sourcePosition) -> sourcePosition.move(0, 1)),

    NE((fileInterval, rankInterval) -> fileInterval - rankInterval == 0,
            (sourcePosition) -> sourcePosition.move(1, 1)),

    E((fileInterval, rankInterval) -> fileInterval > 0 && rankInterval == 0,
            (sourcePosition) -> sourcePosition.move(1, 0)),

    SE((fileInterval, rankInterval) -> fileInterval - (-rankInterval) == 0,
            (sourcePosition) -> sourcePosition.move(1, -1)),

    S((fileInterval, rankInterval) -> fileInterval == 0 && rankInterval < 0,
            (sourcePosition) -> sourcePosition.move(0, -1)),

    SW((fileInterval, rankInterval) -> (-fileInterval) - (-rankInterval) == 0,
            (sourcePosition) -> sourcePosition.move(-1, -1)),

    W((fileInterval, rankInterval) -> fileInterval < 0 && rankInterval == 0,
            (sourcePosition) -> sourcePosition.move(-1, 0)),

    NW((fileInterval, rankInterval) -> (-fileInterval) - rankInterval == 0,
            (sourcePosition) -> sourcePosition.move(-1, 1));

    private BiPredicate<Integer, Integer> isSameDirection;
    private UnaryOperator<Position> moveByUnit;

    MoveDirection(BiPredicate<Integer, Integer> isSameDirection, UnaryOperator<Position> moveByUnit) {
        this.isSameDirection = isSameDirection;
        this.moveByUnit = moveByUnit;
    }


    public boolean isSameDirection(Position sourcePosition, Position targetPosition) {
        int fileInterval = sourcePosition.calculateFileIntervalTo(targetPosition);
        int rankInterval = sourcePosition.calculateRankIntervalTo(targetPosition);

        return this.isSameDirection.test(fileInterval, rankInterval);
    }

    public Position move(Position sourcePosition) {
        Objects.requireNonNull(sourcePosition, "유효한 위치가 아닙니다.");

        return moveByUnit.apply(sourcePosition);
    }
}
