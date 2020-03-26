package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NonLeapableStrategy implements MovableStrategy {

    protected final List<MoveDirection> movableDirections = new ArrayList<>();

    @Override
    public boolean canMove(Position source, Position target) {
        validate(source, target);
        return canMoveDirection(source, target) && canMoveRange(source, target);
    }

    private void validate(Position source, Position target) {
        Objects.requireNonNull(source, "이동할 소스가 존재하지 않습니다.");
        Objects.requireNonNull(target, "이동할 타겟이 존재하지 않습니다.");
    }

    @Override
    public boolean canLeap() {
        return false;
    }

    private boolean canMoveDirection(Position source, Position target) {
        return movableDirections.stream()
                .anyMatch(moveDirection -> moveDirection.isSameDirection(source, target));
    }

    protected abstract boolean canMoveRange(Position source, Position target);
}
