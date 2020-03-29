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
			Position movablePosition = position;
			while (movablePosition.checkBound(direction)) {
				movablePosition = movablePosition.getMovedPositionBy(direction);
				if (isPossessedBySameColor(movablePosition, pieces, color)) {
					break;
				}
				movablePositions.add(movablePosition);

				if (isPossessed(movablePosition, pieces)) {
					break;
				}
			}
		}
		return movablePositions;
	}

	private boolean isPossessed(Position movablePosition, List<Piece> pieces) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(movablePosition));
	}

	private boolean isPossessedBySameColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}
