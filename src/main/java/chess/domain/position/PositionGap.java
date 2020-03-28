package chess.domain.position;

import java.util.List;
import java.util.Objects;

import chess.domain.Direction;

public class PositionGap {
	private static final String WRONG_POSITION_MESSAGE = "올지않은 좌표입니다.";
	private static final int START_COUNT = 1;
	private static final int KNIGHT_MOVE_FIRST_VALUE = 1;
	private static final int KNIGHT_MOVE_SECOND_VALUE = 2;

	private final int x;
	private final int y;

	public PositionGap(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Direction calculateDirection(List<Direction> directions) {
		return directions.stream()
			.filter(direction -> direction.isMatch(x, y, START_COUNT))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(WRONG_POSITION_MESSAGE));
	}

	public boolean cannotMoveKnight() {
		return canMoveKnight() == false;

	}

	private boolean canMoveKnight() {
		int absX = Math.abs(x);
		int absY = Math.abs(y);
		return (absX == KNIGHT_MOVE_SECOND_VALUE && absY == KNIGHT_MOVE_FIRST_VALUE)
			|| (absX == KNIGHT_MOVE_FIRST_VALUE && absY == KNIGHT_MOVE_SECOND_VALUE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PositionGap that = (PositionGap)o;
		return x == that.x &&
			y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
