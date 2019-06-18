package chess.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.Direction;
import chess.Path;
import chess.Position;
import chess.exception.NotFoundPathException;

public class RookPattern implements Pattern {
	private final List<Direction> directions = Arrays.asList(
			Direction.TOP,
			Direction.BOTTOM,
			Direction.LEFT,
			Direction.RIGHT);

	@Override
	public Path move(final Position start, final Position end) {
		for (Direction direction : directions) {
			if (start.canMove(end, direction)) {
				return getPath(start, end, direction);
			}
		}
		throw new NotFoundPathException();
	}

	private Path getPath(final Position start, final Position end, final Direction direction) {
		List<Position> path = new ArrayList<>();

		Position next = start;
		while (!next.equals(end)) {
			path.add(next);
			next = next.move(direction);
		}
		path.add(next);
		return new Path(path);
	}
}
