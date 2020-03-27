package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KnightMovable implements Movable {
	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Directions moveDirections = Directions.KNIGHT;
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : moveDirections.getDirections()) {
			Position movablePosition = position.getMovedPositionBy(direction);
			if (!position.equals(movablePosition) && checkMovable(movablePosition, pieces, color)) {
				movablePositions.add(movablePosition);
			}
		}
		return movablePositions;
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
