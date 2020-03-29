package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnMovable implements Movable {
	private final Directions moveDirections;

	public PawnMovable(Directions moveDirections) {
		this.moveDirections = moveDirections;
	}

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		//대각선 체크
		Set<Position> movablePositions =
				moveDirections.getDirections()
						.stream()
						.map(position::getMovedPositionBy)
						.filter(movablePosition -> !position.isSameRow(movablePosition) && isPossessedByDifferentColor(movablePosition, pieces, color))
						.collect(Collectors.toSet());

		//직선 경로 구하기
		Direction direction = moveDirections.getDirections()
				.stream()
				.filter(direction1 -> position.isSameRow(position.getMovedPositionBy(direction1)))
				.findFirst()
				.orElseGet(() -> Direction.NONE);

		//직선체크
		Position movablePosition = position.getMovedPositionBy(direction);
		if (isNotPossessed(movablePosition, pieces)) {
			movablePositions.add(movablePosition);
			movablePosition = movablePosition.getMovedPositionBy(direction);
			if (position.isPawnInitial(color) && isNotPossessed(movablePosition, pieces)) {
				movablePositions.add(movablePosition);
			}
		}
		return movablePositions;
	}

	private boolean isNotPossessed(Position movablePosition, List<Piece> pieces) {
		return pieces.stream()
				.noneMatch(piece -> piece.isSamePosition(movablePosition));
	}

	private boolean isPossessedByDifferentColor(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
	}
}
