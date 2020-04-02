package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;

public class Path {
	private final int INITIAL_MOVABLE_PAWN_DISTANCE = 2;

	private final List<Position> positions;
	private final Position sourcePosition;

	public Path(List<Position> positions, Position sourcePosition) {
		this.positions = positions;
		this.sourcePosition = sourcePosition;
	}

	public void findPathManyTimesByDirections(List<Direction> directions, Map<Position, Piece> pieces) {
		int locationOfX = sourcePosition.getColumn();
		int locationOfY = sourcePosition.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			while (!isOutOfBoundPosition(afterMoveOfX, afterMoveOfY)) {
				if (isNotBlankPosition(pieces, afterMoveOfX, afterMoveOfY) && isNotSameColorSourceAndAfterMove(pieces,
					afterMoveOfX, afterMoveOfY)) {
					positions.add(Position.of(afterMoveOfX, afterMoveOfY));
					break;
				}
				if (isNotBlankPosition(pieces, afterMoveOfX, afterMoveOfY)) {
					break;
				}

				positions.add(Position.of(afterMoveOfX, afterMoveOfY));

				afterMoveOfX += direction.getX();
				afterMoveOfY += direction.getY();
			}

		}
	}

	private boolean isNotSameColorSourceAndAfterMove(Map<Position, Piece> pieces, int afterMoveOfX, int afterMoveOfY) {
		return !pieces.get(sourcePosition).isSameColor(pieces.get(Position.of(afterMoveOfX, afterMoveOfY)).getColor());
	}

	private boolean isOutOfBoundPosition(int afterMoveOfX, int afterMoveOfY) {
		try {
			Position.of(afterMoveOfX, afterMoveOfY);
		} catch (IllegalArgumentException e) {
			return true;
		}
		return false;
	}

	private boolean isNotBlankPosition(Map<Position, Piece> pieces, int afterMoveOfX, int afterMoveOfY) {
		return !pieces.get(Position.of(afterMoveOfX, afterMoveOfY)).isBlank();
	}

	public void findPathOneTimeByDirections(List<Direction> directions, Map<Position, Piece> pieces) {
		int locationOfX = sourcePosition.getColumn();
		int locationOfY = sourcePosition.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			if (isOutOfBoundPosition(afterMoveOfX, afterMoveOfY)) {
				continue;
			}

			if (isNotBlankPosition(pieces, afterMoveOfX, afterMoveOfY) && !isNotSameColorSourceAndAfterMove(pieces,
				afterMoveOfX, afterMoveOfY)) {
				continue;
			}
			positions.add(Position.of(afterMoveOfX, afterMoveOfY));
		}
	}

	public void findPathPawnByDirections(List<Direction> moveDirections, List<Direction> attackDirections,
		Map<Position, Piece> pieces) {
		int locationOfX = sourcePosition.getColumn();
		int locationOfY = sourcePosition.getRank();

		if (sourcePosition.isInitialPawnPosition(pieces.get(sourcePosition).getColor())) {
			for (int i = 1; i <= INITIAL_MOVABLE_PAWN_DISTANCE; i++) {
				int afterMoveOfY = locationOfY + i;
				if (!isNotBlankPosition(pieces, locationOfX, afterMoveOfY)) {
					positions.add(Position.of(locationOfX, afterMoveOfY));
				}
			}
		} else {
			for (Direction direction : moveDirections) {
				int afterMoveOfX = locationOfX + direction.getX();
				int afterMoveOfY = locationOfY + direction.getY();
				if (isOutOfBoundPosition(afterMoveOfX, afterMoveOfY)) {
					continue;
				}
				if (!isNotBlankPosition(pieces, afterMoveOfX, afterMoveOfY)) {
					positions.add(Position.of(afterMoveOfX, afterMoveOfY));
				}
			}
		}

		for (Direction direction : attackDirections) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();
			if (isOutOfBoundPosition(afterMoveOfX, afterMoveOfY)) {
				continue;
			}
			if (isNotBlankPosition(pieces, afterMoveOfX, afterMoveOfY) && isNotSameColorSourceAndAfterMove(pieces,
				afterMoveOfX, afterMoveOfY)) {
				positions.add(Position.of(afterMoveOfX, afterMoveOfY));
			}
		}
	}

	public boolean isMovable(Position target) {
		return positions.contains(target);
	}

	public List<Position> getPositions() {
		return positions;
	}
}
