package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public class StraightPattern implements MovePattern {
	private static final int ZERO = 0;

	private final Direction direction;
	private final int count;

	public StraightPattern(Position source, Position target) {
		this.direction = findDirection(source, target);
		this.count = findCount(source, target);
	}

	public MovePattern valueOf(Position source, Position target) {
		return new StraightPattern(source, target);
	}

	private int findCount(Position source, Position target) {
		return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
	}

	private Direction findDirection(Position source, Position target) {
		if (source.isSameFile(target)) {
			if (source.calculateRankDistance(target) > ZERO) {
				return Direction.DOWN;
			}
			return Direction.UP;
		}
		if (source.calculateFileDistance(target) > ZERO) {
			return Direction.LEFT;
		}
		return Direction.RIGHT;
	}

	@Override
	public boolean isMatchedPoints(Position source, Position target) {
		return source.isSameRank(target) || source.isSameFile(target);
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
