package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.coordinates.Coordinates;
import chess.domain.coordinates.Direction;
import chess.exception.PieceMoveFailedException;

public abstract class MovablePiece extends AbstractPiece {
	public MovablePiece(List<Direction> movableDirections, String name, Color color, double score) {
		super(movableDirections, name, color, score);
	}

	@Override
	public List<Coordinates> findMovableCoordinates(Coordinates from, Coordinates to) {
		Direction direction = Direction.of(from, to);
		if (!movableDirections.contains(direction)) {
			throw new PieceMoveFailedException("이동할 수 없는 위치입니다.");
		}
		return Collections.singletonList(from.next(direction));
	}
}
