package chess.domain.piece.movestrategy;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class PawnMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
		Direction direction = Direction.of(from, to);

		if (direction.isVertical()) {
			return moveVertical(value, from, to, direction);
		}

		return moveDiagonal(value, from, to, direction);
	}

	private boolean moveVertical(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
		Coordinate nextCoordinate = from.next(direction);
		Piece nextPiece = value.get(nextCoordinate);
		if (!nextPiece.isEmpty()) {
			return false;
		}

		if (nextCoordinate == to) {
			// 바로 앞의 칸이 비어 있다면 앞으로 한 칸 전진할 수 있다.
			return true;
		}
		Piece fromPiece = value.get(from);
		if (from.isPawnStartRow(fromPiece.getTeam())) {
			// 경기중 단 한번도 움직이지 않은 폰은 바로 앞의 두칸이 비어 있을 때 두칸 전진할 수 있다.(한칸만 전진해도 된다.)
			nextCoordinate = nextCoordinate.next(direction);
			nextPiece = value.get(nextCoordinate);
			return nextPiece.isEmpty() && nextCoordinate == to;
		}
		return false;

	}

	private boolean moveDiagonal(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
		Coordinate nextCoordinate = from.next(direction);
		Piece toPiece = value.get(to);
		return !toPiece.isEmpty() && nextCoordinate == to;
	}
}
