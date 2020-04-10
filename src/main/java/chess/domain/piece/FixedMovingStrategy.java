package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public class FixedMovingStrategy implements MovingStrategy {
	private List<Direction> movableDirections;

	public FixedMovingStrategy(List<Direction> movableDirections) {
		this.movableDirections = movableDirections;
	}

	@Override
	public Set<Position> findMovablePositions(Position currentPosition, Map<Position, Piece> pieces) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : movableDirections) {
			movablePositions.addAll(findNext(currentPosition, direction, pieces));
		}
		return movablePositions;
	}

	protected Set<Position> findNext(Position currentPosition, Direction direction,
		Map<Position, Piece> pieces) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = pieces.get(currentPosition);
		if (!currentPosition.canMoveNext(direction)) {
			return movablePositions;
		}
		currentPosition = currentPosition.next(direction);
		if (pieces.get(currentPosition) == null) {
			movablePositions.add(currentPosition);
		}
		if (pieces.get(currentPosition) != null && target.isEnemy(pieces.get(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
