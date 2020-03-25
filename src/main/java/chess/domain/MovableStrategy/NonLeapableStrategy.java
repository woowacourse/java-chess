package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class NonLeapableStrategy implements MovableStrategy {

    protected final List<MoveDirection> movableDirections = new ArrayList<>();

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveDirection(source, target) && canMoveRange(source, target);
    }

    @Override
    public boolean canLeap(Position source, Position target) {
        return false;
    }

    private boolean canMoveDirection(Position source, Position target) {
        return movableDirections.stream()
                .anyMatch(moveDirection -> moveDirection.isSameDirection(source, target));
    }

    abstract boolean canMoveRange(Position source, Position target);
}
