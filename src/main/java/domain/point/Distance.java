package domain.point;

public enum Distance {
	ONE,
	TWO_VERTICAL,
	ELSE;

	public static Distance of(Point a, Point b) {
		int rowDifference = Math.abs(b.getRowIndex() - a.getRowIndex());
		int columnDifference = Math.abs(b.getColumnIndex() - a.getColumnIndex());

		if (rowDifference == 1 && columnDifference == 1) {
			return ONE;
		}
		if (rowDifference == 1 && columnDifference == 0) {
			return ONE;
		}
		if (rowDifference == 0 && columnDifference == 1) {
			return ONE;
		}
		if (rowDifference == 2 && columnDifference == 0) {
			return TWO_VERTICAL;
		}
		return ELSE;
	}
}
