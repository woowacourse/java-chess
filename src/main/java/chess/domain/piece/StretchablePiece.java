package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.coordinates.Coordinates;
import chess.domain.coordinates.Direction;

public abstract class StretchablePiece extends AbstractPiece {
	public StretchablePiece(List<Direction> movableDirections, String name, Color color, double score) {
		super(movableDirections, name, color, score);
	}

	@Override
	public List<Coordinates> findMovableCoordinates(Coordinates from, Coordinates to) {
		Direction direction = Direction.of(from, to);
		validateDirection(direction);

		List<Coordinates> positions = new ArrayList<>();
		while (!from.equals(to)) {
			from = from.next(direction);
			positions.add(from);
		}
		return positions;
	}

	private void validateDirection(Direction direction) {
		if (!movableDirections.contains(direction)) {
			throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
		}
	}
}
