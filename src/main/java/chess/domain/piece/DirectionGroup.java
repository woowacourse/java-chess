package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.MovePosition;

import java.util.ArrayList;
import java.util.List;

public class DirectionGroup {
    
    private static final int CANNOT_MOVE_LENGTH = 0;
    private static final int MAX_MOVABLE_LENGTH_OF_PAWN = 2;
    
    private static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    
    private final List<Direction> directionGroup;
    private final int movableLength;
    
    public DirectionGroup(List<Direction> directionGroup, int movableLength) {
        this.directionGroup = directionGroup;
        this.movableLength = movableLength;
    }
    
    public static DirectionGroup createBlankDirectionGroup() {
        return new DirectionGroup(new ArrayList<>(), CANNOT_MOVE_LENGTH);
    }
    
    public Direction findDirection(MovePosition movePosition) {
        return directionGroup.stream()
                             .filter(direction -> movePosition.canMove(direction, movableLength))
                             .findAny()
                             .orElseThrow(() -> new IllegalArgumentException(ERROR_CAN_NOT_MOVE));
    }
    
    public Direction findDirectionOfPawn(MovePosition movePosition, Color color) {
        final Direction forwardDirection = color.getPawnForwardDirection();
        final boolean isAtDefaultPosition = movePosition.isAtDefaultPawnPosition(color);
        final boolean canMoveTwoSteps = movePosition.canMove(forwardDirection, MAX_MOVABLE_LENGTH_OF_PAWN);
        
        if (isAtDefaultPosition && canMoveTwoSteps) {
            return forwardDirection;
        }
        return findDirection(movePosition);
    }
}
