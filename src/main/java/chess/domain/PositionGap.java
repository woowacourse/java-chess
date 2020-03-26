package chess.domain;

import java.util.List;

public class PositionGap {
	private static final String WRONG_POSITION_MESSAGE = "올지않은 좌표입니다.";
	private static final int START_COUNT = 1;
	private static final int KNIGHT_MOVE_VALUE = 1;

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
		return Math.abs(Math.abs(x) - Math.abs(y)) != KNIGHT_MOVE_VALUE;

	}

}
