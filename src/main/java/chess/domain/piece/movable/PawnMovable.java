package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;
import java.util.stream.Collectors;

public class PawnMovable implements Movable {
	private final Directions moveDirections;

	public PawnMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
		Positions movablePositions = getMovableDiagonalDirectionPositions(position, pieces, color);

		Direction direction = getForwardDirection(position);
		Position movableForwardPosition = position.getMovedPositionBy(direction);

		if (isNotPossessed(movableForwardPosition, pieces)) {
			movablePositions.add(movableForwardPosition);
			movableForwardPosition = movableForwardPosition.getMovedPositionBy(direction);
			if (position.isPawnInitial(color) && isNotPossessed(movableForwardPosition, pieces)) {
				movablePositions.add(movableForwardPosition);
			}
		}

		return movablePositions;
	}

	private Positions getMovableDiagonalDirectionPositions(Position position, List<Piece> pieces, Color color) {
		return moveDirections.getDirections()
				.stream()
				.map(position::getMovedPositionBy)
				.filter(movablePosition -> position.isDifferentRow(movablePosition) && isPossessedByDifferentColor(movablePosition, pieces, color))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Positions::new));
	}

	private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
	}

	private Direction getForwardDirection(Position position) {
		return moveDirections.getDirections()
				.stream()
				.filter(direction -> position.isSameRow(position.getMovedPositionBy(direction)))
				.findFirst()
				.orElse(Direction.NONE);
	}

	private boolean isNotPossessed(Position movablePosition, List<Piece> pieces) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(movablePosition));
	}
}
