package chess.domain.coordinates;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Direction {
	UP(0, 1, (columnGap, rowGap) -> columnGap == 0 && rowGap > 0),
	DOWN(0, -1, (columnGap, rowGap) -> columnGap == 0 && rowGap < 0),
	LEFT(-1, 0, (columnGap, rowGap) -> columnGap < 0 && rowGap == 0),
	RIGHT(1, 0, (columnGap, rowGap) -> columnGap > 0 && rowGap == 0),

	LEFT_UP(-1, 1, (columnGap, rowGap) -> columnGap < 0 && rowGap > 0 && rowGap.equals(-1 * columnGap)),
	LEFT_DOWN(-1, -1, (columnGap, rowGap) -> columnGap < 0 && rowGap < 0 && rowGap.equals(columnGap)),
	RIGHT_UP(1, 1, (columnGap, rowGap) -> columnGap > 0 && rowGap > 0 && rowGap.equals(columnGap)),
	RIGHT_DOWN(1, -1, (columnGap, rowGap) -> columnGap > 0 && rowGap < 0 && rowGap.equals(-1 * columnGap)),

	LEFT_UP_UP(-1, 2, (columnGap, rowGap) -> columnGap == -1 && rowGap == 2),
	LEFT_DOWN_DOWN(-1, -2, (columnGap, rowGap) -> columnGap == -1 && rowGap == -2),
	LEFT_LEFT_UP(-2, 1, (columnGap, rowGap) -> columnGap == -2 && rowGap == 1),
	LEFT_LEFT_DOWN(-2, -1, (columnGap, rowGap) -> columnGap == -2 && rowGap == -1),

	RIGHT_RIGHT_UP(2, 1, (columnGap, rowGap) -> columnGap == 2 && rowGap == 1),
	RIGHT_RIGHT_DOWN(2, -1, (columnGap, rowGap) -> columnGap == 2 && rowGap == -1),
	RIGHT_UP_UP(1, 2, (columnGap, rowGap) -> columnGap == 1 && rowGap == 2),
	RIGHT_DOWN_DOWN(1, -2, (columnGap, rowGap) -> columnGap == 1 && rowGap == -2);

	private final int columnOffset;
	private final int rowOffset;
	private final BiPredicate<Integer, Integer> match;

	Direction(int columnOffset, int rowOffset, BiPredicate<Integer, Integer> match) {
		this.columnOffset = columnOffset;
		this.rowOffset = rowOffset;
		this.match = match;
	}

	public static Direction of(Coordinates from, Coordinates to) {
		int columnGap = from.calculateColumnGap(to);
		int rowGap = from.calculateRowGap(to);
		return Stream.of(values())
				.filter(direction -> direction.matches(columnGap, rowGap))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
	}

	private boolean matches(int rowGap, int columnGap) {
		return match.test(rowGap, columnGap);
	}

	public int getColumnOffset() {
		return columnOffset;
	}

	public int getRowOffset() {
		return rowOffset;
	}
}
