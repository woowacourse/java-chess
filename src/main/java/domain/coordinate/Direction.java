package domain.coordinate;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
	NORTH(-1, 0, DirectionPredicates::isN),
	NORTH_EAST(-1, 1, DirectionPredicates::isNe),
	EAST(0, 1, DirectionPredicates::isE),
	SOUTH_EAST(1, 1, DirectionPredicates::isSe),
	SOUTH(1, 0, DirectionPredicates::isS),
	SOUTH_WEST(1, -1, DirectionPredicates::isSw),
	WEST(0, -1, DirectionPredicates::isW),
	NORTH_WEST(-1, -1, DirectionPredicates::isNw),
	KNIGHT(0, 0, DirectionPredicates::isKnight),
	ELSE(0, 0, DirectionPredicates::isElse);

	private final int rowIndex;
	private final int columnIndex;
	private final BiPredicate<Integer, Integer> biPredicate;

	Direction(int rowIndex, int columnIndex, BiPredicate<Integer, Integer> biPredicate) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.biPredicate = biPredicate;
	}

	public static Direction of(Coordinate from, Coordinate to) {
		int rowDifference = getRowDifference(from, to);
		int columnDifference = getColumnDifference(from, to);

		return Arrays.stream(values())
				.filter(direction -> direction.biPredicate.test(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}

	private static int getRowDifference(Coordinate from, Coordinate to) {
		return to.getRowIndex() - from.getRowIndex();
	}

	private static int getColumnDifference(Coordinate from, Coordinate to) {
		return to.getColumnIndex() - from.getColumnIndex();
	}

	public boolean isNotStraight() {
		return !Arrays.asList(EAST, WEST, SOUTH, NORTH).contains(this);
	}

	public boolean isNotDiagonalUp() {
		return !isDiagonalUp();
	}

	private boolean isDiagonalUp() {
		return this == NORTH_WEST || this == NORTH_EAST;
	}

	public boolean isNotDiagonalDown() {
		return !isDiagonalDown();
	}

	private boolean isDiagonalDown() {
		return this == SOUTH_WEST || this == SOUTH_EAST;
	}

	public boolean isNotDiagonal() {
		return isNotDiagonalUp() && isNotDiagonalDown();
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
