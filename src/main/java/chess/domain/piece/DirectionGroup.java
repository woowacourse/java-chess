package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class DirectionGroup {
    
    private static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    
    private final List<Direction> directionGroup;
    private final int movableLength;
    
    public DirectionGroup(List<Direction> directionGroup, int movableLength) {
        this.directionGroup = directionGroup;
        this.movableLength = movableLength;
    }
    
    public static DirectionGroup createBlankDirectionGroup() {
        return new DirectionGroup(new ArrayList<>(), 0);
    }
    
    public Direction findDirection(Position sourcePosition, Position targetPosition) {
        return directionGroup.stream()
                             .filter(direction -> sourcePosition.canMove(targetPosition, direction, movableLength))
                             .findAny()
                             .orElseThrow(() -> new IllegalArgumentException(ERROR_CAN_NOT_MOVE));
    }
    
    public Direction findDirectionOfPawn(Position sourcePosition, Position targetPosition, Color color) {
        final int maxMovableLength = 2;
        final Direction forwardDirection = color.getPawnForwardDirection();
        final boolean isAtDefaultPosition = sourcePosition.isAtDefaultPawnPosition(color);
        final boolean canMoveTwoSteps = sourcePosition.canMove(targetPosition, forwardDirection, maxMovableLength);
        
        if (isAtDefaultPosition && canMoveTwoSteps) {
            return forwardDirection;
        }
        
        return findDirection(sourcePosition, targetPosition);
    }
}
