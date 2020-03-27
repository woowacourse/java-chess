package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnblockedMovable implements Movable {
	private final Directions moveDirections;

	public UnblockedMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : moveDirections.getDirections()) {
			Position movablePosition = position.getMovedPositionBy(direction);
			if (checkMovable(movablePosition, pieces, color)) {
				movablePositions.add(movablePosition);
			}
		}
		return movablePositions;
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}
