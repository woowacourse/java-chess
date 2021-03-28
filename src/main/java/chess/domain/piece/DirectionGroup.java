package chess.domain.piece;

import chess.domain.position.MovePosition;

import java.util.List;

public class DirectionGroup {
    
    private static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    
    private final List<Direction> directionGroup;
    private final int movableLength;
    
    public DirectionGroup(List<Direction> directionGroup, int movableLength) {
        this.directionGroup = directionGroup;
        this.movableLength = movableLength;
    }
    
    public Direction findDirection(MovePosition movePosition) {
        return directionGroup.stream()
                             .filter(direction -> movePosition.canMove(direction, movableLength))
                             .findAny()
                             .orElseThrow(() -> new IllegalArgumentException(ERROR_CAN_NOT_MOVE));
    }
}
