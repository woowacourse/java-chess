package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockedMovable implements Movable {
	private final Directions moveDirections;

	public BlockedMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : moveDirections.getDirections()) {
			Set<Position> positions = createMovablePositionsByDirection(position, direction, pieces, color);
			movablePositions.addAll(positions);
		}
		return movablePositions;
	}

	Set<Position> createMovablePositionsByDirection(Position movablePosition, Direction direction, List<Piece> pieces, Color color) {
		Set<Position> movablePositions = new HashSet<>();
		while (isOpen(movablePosition, direction, pieces, color)) {
			movablePosition = movablePosition.getMovedPositionBy(direction);
			movablePositions.add(movablePosition);
		}
		return movablePositions;
	}

	private boolean isOpen(Position movablePosition, Direction direction, List<Piece> pieces, Color color) {
		if (!movablePosition.checkBound(direction)) {
			return false;
		}
		if (isPossessedByDifferentColor(movablePosition, pieces, color)) {
			return false;
		}
		return !isPossessedBySameColor(movablePosition.getMovedPositionBy(direction), pieces, color);
	}

	private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
	}

	private boolean isPossessedBySameColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}
