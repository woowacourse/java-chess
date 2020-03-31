package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public class StretchMovingStrategy implements MovingStrategy {
	protected List<Direction> movableDirections;

	public StretchMovingStrategy(List<Direction> movableDirections) {
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

	public Set<Position> findNext(Position startPosition, Direction direction, Function<Position, Piece> pieceFinder) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = pieceFinder.apply(startPosition);

		Position currentPosition = startPosition;
		while (currentPosition.canMoveNext(direction)) {
			currentPosition = currentPosition.next(direction);
			if (pieceFinder.apply(currentPosition) != null) {
				break;
			}
			movablePositions.add(currentPosition);
		}
		if (pieceFinder.apply(currentPosition) != null && target.isEnemy(pieceFinder.apply(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
