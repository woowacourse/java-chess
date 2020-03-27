package chess.domain.piece;

import java.util.Iterator;

public class Path implements Iterator<Position> {
	private Position source;
	private Position target;
	private Direction direction;

	public Path(Position source, Position target, Direction direction) {
		this.source = source;
		this.target = target;
		this.direction = direction;
	}

	@Override
	public boolean hasNext() {
		return source.add(direction.getX(), direction.getY()) != target;
	}

	@Override
	public Position next() {
		return source = source.add(direction.getX(), direction.getY());
	}
}
