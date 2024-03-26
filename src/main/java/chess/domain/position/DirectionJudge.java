package chess.domain.position;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static chess.domain.position.Direction.*;

public enum DirectionJudge {
    UP_CONDITION(UP, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isDownPosition(destination)),
    DOWN_CONDITION(DOWN, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isUpPosition(destination)),
    LEFT_CONDITION(LEFT, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isRightPosition(destination)),
    RIGHT_CONDITION(RIGHT, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isLeftPosition(destination)),
    UP_RIGHT_CONDITION(UP_RIGHT, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isDownPosition(destination) && start.isLeftPosition(destination)),
    UP_LEFT_CONDITION(UP_LEFT, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isDownPosition(destination) && start.isRightPosition(destination)),
    DOWN_RIGHT_CONDITION(DOWN_RIGHT, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isUpPosition(destination) && start.isLeftPosition(destination)),
    DOWN_LEFT_CONDITION(DOWN_LEFT, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isUpPosition(destination) && start.isRightPosition(destination)),
    ;

    private final Direction direction;
    private final BiPredicate<Position, Position> directionCondition;

    DirectionJudge(Direction direction, BiPredicate<Position, Position> directionCondition) {
        this.direction = direction;
        this.directionCondition = directionCondition;
    }

    // TODO 예외에 메시지 담아 던지기
    public static Direction judge(Position start, Position destination) {
        return Stream.of(values())
                .filter(condition -> condition.directionCondition.test(start, destination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("두 위치의 방향을 특정할 수 없습니다."))
                .direction;
    }
}
