package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public class FixedMovingStrategy implements MovingStrategy {
	private List<Direction> movableDirections;

	public FixedMovingStrategy(List<Direction> movableDirections) {
		this.movableDirections = movableDirections;
	}

	@Override
	public Set<Position> findMovablePositions(Position currentPosition, Function<Position, Piece> pieceFinder) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : movableDirections) {
			movablePositions.addAll(findNext(currentPosition, direction, pieceFinder));
		}
		return movablePositions;
	}

	protected Set<Position> findNext(Position currentPosition, Direction direction,
		Function<Position, Piece> pieceFinder) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = pieceFinder.apply(currentPosition);
		if (!currentPosition.canMoveNext(direction)) {
			return movablePositions;
		}
		currentPosition = currentPosition.next(direction);
		if (pieceFinder.apply(currentPosition) == null) {
			movablePositions.add(currentPosition);
		}
		if (pieceFinder.apply(currentPosition) != null && target.isEnemy(pieceFinder.apply(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
