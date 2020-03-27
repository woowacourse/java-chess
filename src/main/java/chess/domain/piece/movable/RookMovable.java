package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RookMovable implements Movable {
	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Directions moveDirections = Directions.LINEAR;
		Set<Position> movablePositions = new HashSet<>();

		for (Direction direction : moveDirections.getDirections()) {
			Position movablePosition = position;
			Position lastPosition = position;
			while (true) {
				movablePosition = movablePosition.getMovedPositionBy(direction);
				if (!lastPosition.equals(movablePosition)) {
					break;
				}

				lastPosition = movablePosition;

				if (!checkMovable(movablePosition, pieces, color)) {
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
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(movablePosition)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(position) && piece.getColor().isSame(color)) {
				return false;
			}
		}
		return true;
	}
}
