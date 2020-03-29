package domain.point;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum  Direction {
	N(-1, 0, DirectionFilters::isN),
	NE(-1, 1, DirectionFilters::isNe),
	E(0, 1,DirectionFilters::isE),
	SE(1, 1, DirectionFilters::isSe),
	S(1, 0, DirectionFilters::isS),
	SW(1, -1, DirectionFilters::isSw),
	W(0, -1, DirectionFilters::isW),
	NW(-1, -1, DirectionFilters::isNw),
	KNIGHT(0, 0, DirectionFilters::isKnight),
	ELSE(0, 0, DirectionFilters::isElse);

	private final int rowIndex;
	private final int columnIndex;
	private final BiFunction<Integer, Integer, Boolean> directionFilter;

	Direction(int rowIndex, int columnIndex, BiFunction<Integer, Integer, Boolean> directionFilter) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.directionFilter = directionFilter;
	}

	public static Direction of(Point from, Point to) {
		int rowDifference = getRowDifference(from, to);
		int columnDifference = getColumnDifference(from, to);

		return Arrays.stream(values())
				.filter(direction -> direction.directionFilter.apply(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}

	private static int getRowDifference(Point from, Point to) {
		return to.getRowIndex() - from.getRowIndex();
	}

	private static int getColumnDifference(Point from, Point to) {
		return to.getColumnIndex() - from.getColumnIndex();
	}

	public boolean isNotStraight() {
		return !Arrays.asList(E, W, S, N).contains(this);
	}

	public boolean isNotDiagonal() {
		return !Arrays.asList(NE, NW, SE, SW).contains(this);
	}

	public boolean isLinearDirection() {
		return !isNotLinearDirection();
	}

	public boolean isNotLinearDirection() {
		return isNotStraight() && isNotDiagonal();
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}
}
