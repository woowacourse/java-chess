package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.RuleStrategy.Rule;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NonLeapable implements Rule {

    protected final List<MoveDirection> movableDirections = new ArrayList<>();

    @Override
    public boolean canLeap() {
        return false;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition, targetPosition);
        return canMoveDirection(sourcePosition, targetPosition) && canMoveRange(sourcePosition, targetPosition);
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        Objects.requireNonNull(sourcePosition, "소스 위치가 존재하지 않습니다.");
        Objects.requireNonNull(targetPosition, "타겟 위치가 존재하지 않습니다.");
    }

    protected boolean canMoveDirection(Position sourcePosition, Position targetPosition) {
        return movableDirections.stream()
                .anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
    }

    protected abstract boolean canMoveRange(Position source, Position target);

}
