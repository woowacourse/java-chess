package domain.path.direction;

import domain.path.PieceMove;
import java.util.Arrays;
import java.util.function.Predicate;

public enum Direction {

    RIGHT_UP(1, 1, DirectionCheckerFactory::isRightUp),
    RIGHT_DOWN(1, -1, DirectionCheckerFactory::isRightDown),
    LEFT_UP(-1, 1, DirectionCheckerFactory::isLeftUp),
    LEFT_DOWN(-1, -1, DirectionCheckerFactory::isLeftDown),
    RIGHT(1, 0, DirectionCheckerFactory::isRight),
    LEFT(-1, 0, DirectionCheckerFactory::isLeft),
    DOWN(0, -1, DirectionCheckerFactory::isDown),
    UP(0, 1, DirectionCheckerFactory::isUp),
    UP_UP_LEFT(-1, 2, DirectionCheckerFactory::isTwoUpAndLeft),
    UP_UP_RIGHT(1, 2, DirectionCheckerFactory::isTwoUpAndRight),
    DOWN_DOWN_LEFT(-1, -2, DirectionCheckerFactory::isTwoDownAndLeft),
    DOWN_DOWN_RIGHT(1, -2, DirectionCheckerFactory::isTwoDownAndRight),
    LEFT_LEFT_UP(-2, 1, DirectionCheckerFactory::isTwoLeftAndUp),
    LEFT_LEFT_DOWN(-2, -1, DirectionCheckerFactory::isTwoLeftAndDown),
    RIGHT_RIGHT_UP(2, 1, DirectionCheckerFactory::isTwoRightAndUp),
    RIGHT_RIGHT_DOWN(2, -1, DirectionCheckerFactory::isTwoRightAndDown);

    private final int colDiff;
    private final int rowDiff;
    private final Predicate<PieceMove> condition;

    Direction(final int colDiff, final int rowDiff, final Predicate<PieceMove> condition) {
        this.colDiff = colDiff;
        this.rowDiff = rowDiff;
        this.condition = condition;
    }

    public static Direction find(final PieceMove pieceMove) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.condition.test(pieceMove))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 경로 입니다."));
    }

    public int getColDiff() {
        return colDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }
}
