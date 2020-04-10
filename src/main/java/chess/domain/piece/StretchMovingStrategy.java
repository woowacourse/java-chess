package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public class StretchMovingStrategy implements MovingStrategy {
	protected List<Direction> movableDirections;

	public StretchMovingStrategy(List<Direction> movableDirections) {
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

	public Set<Position> findNext(Position startPosition, Direction direction, Map<Position, Piece> pieces) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = pieces.get(startPosition);

		Position currentPosition = startPosition;
		while (currentPosition.canMoveNext(direction)) {
			currentPosition = currentPosition.next(direction);
			if (pieces.get(currentPosition) != null) {
				break;
			}
			movablePositions.add(currentPosition);
		}
		if (pieces.get(currentPosition) != null && target.isEnemy(pieces.get(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
