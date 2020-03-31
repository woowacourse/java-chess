package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;
import java.util.stream.Collectors;

public class UnblockedMovable implements Movable {
	private final Directions moveDirections;

	public UnblockedMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
		return moveDirections.getDirections().stream()
				.map(position::getMovedPositionBy)
				.filter(movablePosition -> checkMovable(movablePosition, pieces, color))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Positions::new));
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(position) && piece.isSameColor(color));
	}
}
