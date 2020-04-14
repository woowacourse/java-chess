package domain.coordinate;

public class DirectionPredicates {

	public static boolean isKnight(int rowDifference, int columnDifference) {
		int absRow = Math.abs(rowDifference);
		int absColumn = Math.abs(columnDifference);

		if (absRow == 2 && absColumn == 1) {
			return true;
		}
		return absRow == 1 && absColumn == 2;
	}

	public static boolean isW(int rowDifference, int columnDifference) {
		if (rowDifference == 0) {
			return columnDifference < 0;
		}
		return false;
	}

	public static boolean isE(int rowDifference, int columnDifference) {
		if (rowDifference == 0) {
			return columnDifference > 0;
		}
		return false;
	}

	public static boolean isN(int rowDifference, int columnDifference) {
		if (columnDifference == 0) {
			return rowDifference < 0;
		}
		return false;
	}

	public static boolean isS(int rowDifference, int columnDifference) {
		if (columnDifference == 0) {
			return rowDifference > 0;
		}
		return false;
	}

	public static boolean isNw(int rowDifference, int columnDifference) {
		if (hasSameAbsoluteValue(rowDifference, columnDifference)) {
			return rowDifference < 0 && columnDifference < 0;
		}
		return false;
	}

	public static boolean isNe(int rowDifference, int columnDifference) {
		if (hasSameAbsoluteValue(rowDifference, columnDifference)) {
			return rowDifference < 0 && columnDifference > 0;
		}
		return false;
	}

	public static boolean isSw(int rowDifference, int columnDifference) {
		if (hasSameAbsoluteValue(rowDifference, columnDifference)) {
			return rowDifference > 0 && columnDifference < 0;
		}
		return false;
	}

	public static boolean isSe(int rowDifference, int columnDifference) {
		if (hasSameAbsoluteValue(rowDifference, columnDifference)) {
			return rowDifference > 0 && columnDifference > 0;
		}
		return false;
	}

	public static boolean isElse(int rowDifference, int columnDifference) {
		return true;
	}

	private static boolean hasSameAbsoluteValue(int rowDifference, int columnDifference) {
		return Math.abs(rowDifference) == Math.abs(columnDifference);
	}

}
