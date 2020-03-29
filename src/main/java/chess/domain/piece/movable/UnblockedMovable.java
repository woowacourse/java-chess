package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnblockedMovable implements Movable {
	private final Directions moveDirections;

	public UnblockedMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		return moveDirections.getDirections().stream()
				.map(position::getMovedPositionBy)
				.filter(movablePosition -> checkMovable(movablePosition, pieces, color))
				.collect(Collectors.toSet());
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}
