package domain.coordinate;

public class DistancePredicates {
	public static boolean isOne(int rowDifference, int columnDifference) {
		if (rowDifference == 1 && columnDifference == 1) {
			return true;
		}
		if (rowDifference == 1 && columnDifference == 0) {
			return true;
		}
		return rowDifference == 0 && columnDifference == 1;
	}

	public static boolean isVerticalTwo(int rowDifference, int columnDifference) {
		return rowDifference == 2 && columnDifference == 0;
	}

	public static boolean isElse(int rowDifference, int columnDifference) {
		return true;
	}
}
