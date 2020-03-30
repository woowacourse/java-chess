package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public class CrossPattern implements MovePattern {
	private static final int ZERO = 0;

	private final Direction direction;
	private final int count;

	public CrossPattern(Position source, Position target) {
		this.direction = findDirection(source, target);
		this.count = findCount(source, target);
	}

	private int findCount(Position source, Position target) {
		return Math.abs(source.calculateRankDistance(target));
	}

	private Direction findDirection(Position source, Position target) {
		if (source.calculateFileDistance(target) < ZERO && source.calculateRankDistance(target) < ZERO) {
			return Direction.UP_RIGHT;
		}
		if (source.calculateFileDistance(target) > ZERO && source.calculateRankDistance(target) < ZERO) {
			return Direction.UP_LEFT;
		}
		if (source.calculateFileDistance(target) > ZERO && source.calculateRankDistance(target) > ZERO) {
			return Direction.DOWN_LEFT;
		}
		return Direction.DOWN_RIGHT;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public int getCount() {
		return this.count;
	}
}
