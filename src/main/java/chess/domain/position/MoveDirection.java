package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum MoveDirection {
    N((fileGap, rankGap) -> fileGap == 0 && rankGap > 0,
            (sourcePosition) -> sourcePosition.move(0, 1)),

    NE((fileGap, rankGap) -> fileGap.equals(rankGap) && fileGap > 0,
            (sourcePosition) -> sourcePosition.move(1, 1)),

    E((fileGap, rankGap) -> fileGap > 0 && rankGap == 0,
            (sourcePosition) -> sourcePosition.move(1, 0)),

    SE((fileGap, rankGap) -> (fileGap + rankGap == 0) && (fileGap > 0 && rankGap < 0),
            (sourcePosition) -> sourcePosition.move(1, -1)),

    S((fileGap, rankGap) -> fileGap == 0 && rankGap < 0,
            (sourcePosition) -> sourcePosition.move(0, -1)),

    SW((fileGap, rankGap) -> fileGap.equals(rankGap) && fileGap < 0,
            (sourcePosition) -> sourcePosition.move(-1, -1)),

    W((fileGap, rankGap) -> fileGap < 0 && rankGap == 0,
            (sourcePosition) -> sourcePosition.move(-1, 0)),

    NW((fileGap, rankGap) -> (fileGap + rankGap == 0) && (fileGap < 0 && rankGap > 0),
            (sourcePosition) -> sourcePosition.move(-1, 1));

    private BiPredicate<Integer, Integer> isSameDirection;
    private UnaryOperator<Position> moveByUnit;

    MoveDirection(BiPredicate<Integer, Integer> isSameDirection, UnaryOperator<Position> moveByUnit) {
        this.isSameDirection = isSameDirection;
        this.moveByUnit = moveByUnit;
    }

    public boolean isSameDirectionFrom(Position sourcePosition, Position targetPosition) {
        int fileInterval = sourcePosition.calculateChessFileGapTo(targetPosition);
        int rankInterval = sourcePosition.calculateChessRankGapTo(targetPosition);

        return this.isSameDirection.test(fileInterval, rankInterval);
    }

    public Position moveDirection(Position sourcePosition) {
        Objects.requireNonNull(sourcePosition, "유효한 위치가 아닙니다.");
        return moveByUnit.apply(sourcePosition);
    }

    public static MoveDirection findDirectionOf(Position sourcePosition, Position targetPosition) {
        return Arrays.stream(MoveDirection.values())
                .filter(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("체스 피스가 이동할 수 없는 위치를 입력하였습니다."));
    }
}
