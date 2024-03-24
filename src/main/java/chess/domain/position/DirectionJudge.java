package chess.domain.position;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static chess.domain.position.Direction.*;

public enum DirectionJudge {
    N_CONDITION(N, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isDownPosition(destination)),
    S_CONDITION(S, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isUpPosition(destination)),
    W_CONDITION(W, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isRightPosition(destination)),
    E_CONDITION(E, (Position start, Position destination) -> start.isStraightWith(destination)
            && start.isLeftPosition(destination)),
    NE_CONDITION(NE, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isDownPosition(destination) && start.isLeftPosition(destination)),
    NW_CONDITION(NW, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isDownPosition(destination) && start.isRightPosition(destination)),
    SE_CONDITION(SE, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isUpPosition(destination) && start.isLeftPosition(destination)),
    SW_CONDITION(SW, (Position start, Position destination) -> start.isDiagonalWith(destination)
            && start.isUpPosition(destination) && start.isRightPosition(destination)),
    ;

    private final Direction direction;
    private final BiPredicate<Position, Position> directionCondition;

    DirectionJudge(Direction direction, BiPredicate<Position, Position> directionCondition) {
        this.direction = direction;
        this.directionCondition = directionCondition;
    }

    public static Direction judge(Position start, Position destination) {
        return Stream.of(values())
                .filter(condition -> condition.directionCondition.test(start, destination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("두 위치의 방향을 특정할 수 없습니다."))
                .direction;
    }
}
