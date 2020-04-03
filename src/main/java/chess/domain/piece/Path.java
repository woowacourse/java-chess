package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;

public class Path {
	private final int INITIAL_MOVABLE_PAWN_DISTANCE = 1;

	private final List<Position> positions;
	private final Position sourcePosition;

	public Path(List<Position> positions, Position sourcePosition) {
		this.positions = positions;
		this.sourcePosition = sourcePosition;
	}

	public void findPathOneTimeByDirections(List<Direction> directions, Map<Position, Piece> pieces) {
		for (Direction direction : directions) {
			int nextPositionOfX = sourcePosition.getColumn() + direction.getX();
			int nextPositionOfY = sourcePosition.getRow() + direction.getY();
			if (isOutOfBoundPosition(nextPositionOfX, nextPositionOfY)) {
				continue;
			}
			Position nextPosition = Position.of(nextPositionOfX, nextPositionOfY);
			if (isBlankPosition(pieces, nextPosition)
				|| (!isBlankPosition(pieces, nextPosition) && !isSameColorPiece(pieces, nextPosition))) {
				positions.add(nextPosition);
			}
		}
	}

	public void findPathManyTimesByDirections(List<Direction> directions, Map<Position, Piece> pieces) {
		for (Direction direction : directions) {
			int nextPositionOfX = sourcePosition.getColumn() + direction.getX();
			int nextPositionOfY = sourcePosition.getRow() + direction.getY();
			goForwardPath(pieces, direction, nextPositionOfX, nextPositionOfY);
		}
	}

	private void goForwardPath(Map<Position, Piece> pieces, Direction direction, int nextPositionOfX,
		int nextPositionOfY) {
		while (!isOutOfBoundPosition(nextPositionOfX, nextPositionOfY)) {
			Position nextPosition = Position.of(nextPositionOfX, nextPositionOfY);
			addPositionIfNextBlank(pieces, nextPosition);
			if (!isBlankPosition(pieces, nextPosition)) {
				addOtherPieceInNextPosition(pieces, nextPosition);
				break;
			}
			nextPositionOfX += direction.getX();
			nextPositionOfY += direction.getY();
		}
	}

	public void findPathPawnByDirections(List<Direction> moveDirections, List<Direction> attackDirections,
		Map<Position, Piece> pieces) {
		for (int i = 0; i < moveDirections.size(); i++) {
			Direction direction = moveDirections.get(i);
			if (i == INITIAL_MOVABLE_PAWN_DISTANCE && !sourcePosition.isInitialPawnPosition(
				pieces.get(sourcePosition).getColor())) {
				continue;
			}
			int nextPositionOfX = sourcePosition.getColumn() + direction.getX();
			int nextPositionOfY = sourcePosition.getRow() + direction.getY();
			if (isOutOfBoundPosition(nextPositionOfX, nextPositionOfY)) {
				continue;
			}
			Position nextPosition = Position.of(nextPositionOfX, nextPositionOfY);
			addPositionIfNextBlank(pieces, nextPosition);
		}

		for (Direction direction : attackDirections) {
			int nextPositionOfX = sourcePosition.getColumn() + direction.getX();
			int nextPositionOfY = sourcePosition.getRow() + direction.getY();
			if (isOutOfBoundPosition(nextPositionOfX, nextPositionOfY)) {
				continue;
			}
			Position nextPosition = Position.of(nextPositionOfX, nextPositionOfY);
			if (!isBlankPosition(pieces, nextPosition) && !isSameColorPiece(pieces, nextPosition)) {
				positions.add(Position.of(nextPositionOfX, nextPositionOfY));
			}
		}
	}

	private void addPositionIfNextBlank(Map<Position, Piece> pieces, Position nextPosition) {
		if (isBlankPosition(pieces, nextPosition)) {
			positions.add(nextPosition);
		}
	}

	private void addOtherPieceInNextPosition(Map<Position, Piece> pieces, Position nextPosition) {
		if (!isSameColorPiece(pieces, nextPosition)) {
			positions.add(nextPosition);
		}
	}

	private boolean isSameColorPiece(Map<Position, Piece> pieces, Position position) {
		return pieces.get(sourcePosition).isSameColor(pieces.get(position).getColor());
	}

	private boolean isBlankPosition(Map<Position, Piece> pieces, Position position) {
		return pieces.get(position).isBlank();
	}

	private boolean isOutOfBoundPosition(int positionOfX, int positionOfY) {
		return positionOfX > Position.MAX || positionOfX < Position.MIN || positionOfY > Position.MAX
			|| positionOfY < Position.MIN;
	}

	public boolean isMovable(Position target) {
		return positions.contains(target);
	}
}
