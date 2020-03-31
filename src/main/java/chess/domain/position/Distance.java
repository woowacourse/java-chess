package chess.domain.position;

public class Distance {
	private final int distance;

	private Distance(int distance) {
		this.distance = distance;
	}

	public static Distance of(Position start, Position end) {
		int columnDistance = Math.abs(start.getColumnGap(end));
		int rowDistance = Math.abs(start.getRowGap(end));

		if (isDiagonal(columnDistance, rowDistance) || isVertical(columnDistance) || isHorizontal(rowDistance)) {
			return new Distance(Integer.max(columnDistance, rowDistance));
		}
		return new Distance(0);
	}

	private static boolean isDiagonal(int columnDistance, int rowDistance) {
		return columnDistance == rowDistance;
	}

	private static boolean isVertical(int columnDistance) {
		return columnDistance == 0;
	}

	private static boolean isHorizontal(int rowDistance) {
		return rowDistance == 0;
	}

	public int getDistance() {
		return distance;
	}
}
