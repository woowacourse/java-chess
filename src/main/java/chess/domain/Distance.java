package chess.domain;

public class Distance {
	private final int distance;

	private Distance(int distance) {
		this.distance = distance;
	}

	public static Distance of(Position start, Position end) {
		int columnDistance = Math.abs(start.getColumnGap(end));
		int rowDistance = Math.abs(start.getRowGap(end));

		if (columnDistance == rowDistance || columnDistance == 0 || rowDistance == 0) {
			return new Distance(Integer.max(columnDistance, rowDistance));
		}
		return new Distance(0);
	}

	public int getDistance() {
		return distance;
	}
}
