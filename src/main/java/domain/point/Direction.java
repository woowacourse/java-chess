package domain.point;

import java.util.Arrays;

public enum  Direction {
	N(-1, 0),
	NE(-1, 1),
	E(0, 1),
	SE(1, 1),
	S(1, 0),
	SW(1, -1),
	W(0, -1),
	NW(-1, -1),
	ELSE(0, 0);

	private int rowIndex;
	private int columnIndex;

	Direction(int rowIndex, int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public static Direction of(Point from, Point to) {
		int rowDifference = getRowDifference(from, to);
		int columnDifference = getColumnDifference(from, to);

		if (rowDifference == 0) {
			return getHorizon(columnDifference);
		}
		if (columnDifference == 0) {
			return getVertical(rowDifference);
		}
		if (Math.abs(rowDifference) == Math.abs(columnDifference)) {
			return getDiagonal(rowDifference, columnDifference);
		}
		return ELSE;
	}

	private static int getRowDifference(Point from, Point to) {
		return to.getRowIndex() - from.getRowIndex();
	}

	private static int getColumnDifference(Point from, Point to) {
		return to.getColumnIndex() - from.getColumnIndex();
	}

	private static Direction getVertical(int rowDifference) {
		if (rowDifference < 0) {
			return N;
		}
		if (rowDifference > 0) {
			return S;
		}

		throw new IllegalArgumentException();
	}

	private static Direction getHorizon(int columnDifference) {
		if (columnDifference < 0) {
			return W;
		}
		if (columnDifference > 0) {
			return E;
		}
		return ELSE;
	}


	private static Direction getDiagonal(int rowDifference, int columnDifference) {
		if (rowDifference < 0 && columnDifference < 0) {
			return NW;
		}
		if (rowDifference < 0 && columnDifference > 0) {
			return NE;
		}
		if (rowDifference > 0 && columnDifference < 0) {
			return SW;
		}
		if (rowDifference > 0 && columnDifference > 0) {
			return SE;
		}

		throw new IllegalArgumentException();
	}

	public boolean isStraight() {
		return Arrays.asList(E, W, S, N).contains(this);
	}

	public boolean isDiagonal() {
		return Arrays.asList(NE, NW, SE, SW).contains(this);
	}

	public boolean isElse() {
		return this == ELSE;
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}
}
