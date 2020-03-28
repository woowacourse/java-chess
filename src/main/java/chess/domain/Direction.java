package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	UP(1, 0),
	RIGHT_UP(1, 1),
	RIGHT(0, 1),
	RIGHT_DOWN(-1, 1),
	DOWN(-1, 0),
	LEFT_DOWN(-1, -1),
	LEFT(0, -1),
	LEFT_UP(1, -1),
	DOUBLE_UP(2, 0),
	DOUBLE_DOWN(-2, 0);

	private static final int MAX_COUNT = 7;

	private final int x;
	private final int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isMatch(int gapX, int gapY, int count) {
		if (count > MAX_COUNT) {
			return false;
		}
		if (gapX == (x * count) && gapY == (y * count)) {
			return true;
		}
		return isMatch(gapX, gapY, ++count);
	}

	public static List<Direction> getPawnDirections(Team team) {
		if (team == Team.WHITE) {
			return Arrays.asList(UP, LEFT_UP, RIGHT_UP);
		}
		return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN);
	}

	public int plusX(int x) {
		return this.x + x;
	}

	public int plusY(int y) {
		return this.y + y;
	}

}
