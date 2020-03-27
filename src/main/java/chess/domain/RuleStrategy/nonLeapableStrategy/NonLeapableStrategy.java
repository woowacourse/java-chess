package chess.domain.RuleStrategy.nonLeapableStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public abstract class NonLeapableStrategy implements RuleStrategy {

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

	private boolean canMoveDirection(Position sourcePosition, Position targetPosition) {
		return movableDirections.stream()
			.anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	protected abstract boolean canMoveRange(Position source, Position target);

}
