package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PawnMovable implements Movable {
	private static final int WHITE_PAWN_INITIAL_COLUMN = 2;
	private static final int BLACK_PAWN_INITIAL_COLUMN = 7;

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Directions moveDirections = Directions.getPawnDirectionsBy(color);
		Set<Position> movablePositions = new HashSet<>();

		for (Direction direction : moveDirections.getDirections()) {
			Position movablePosition = position.getMovedPositionBy(direction);

			if (position.isSameRow(movablePosition)) { // 같은 로우 위
				if (isPossessed(movablePosition, pieces)) { // 아무도 없을 때
					continue;
				}
				movablePositions.add(movablePosition);
				if (isInitial(position, color)) {
					movablePosition = movablePosition.getMovedPositionBy(direction);
					if (!isPossessed(movablePosition, pieces)) { // 아무도 없을 때
						movablePositions.add(movablePosition);
					}
				}
				continue;
			}
			if (checkMovable(movablePosition, pieces, color)) { // 대각선
				movablePositions.add(movablePosition);
			}
		}
		return movablePositions;
	}

	private boolean isInitial(Position position, Color color) {
		if (position.getColumn().getValue() == WHITE_PAWN_INITIAL_COLUMN && color.isWhite()) {
			return true;
		}
		if (position.getColumn().getValue() == BLACK_PAWN_INITIAL_COLUMN && color.isBlack()) {
			return true;
		}
		return false;
	}

	private boolean isPossessed(Position movablePosition, List<Piece> pieces) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(movablePosition));
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position) && piece.isNotSameColor(color));
	}
}
