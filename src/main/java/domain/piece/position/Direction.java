package domain.piece.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	N(1, 0),
	S(-1, 0),
	E(0, 1),
	W(0, -1),

	NE(1, 1),
	NW(1, -1),
	SE(-1, 1),
	SW(-1, -1),

	NNE(2, 1),
	NNW(2, -1),
	SSE(-2, 1),
	SSW(-2, -1),
	NEE(1, 2),
	NWW(1, -2),
	SEE(-1, 2),
	SWW(-1, -2);

	private int rowGap;
	private int columnGap;

	Direction(int rowGap, int columnGap) {
		this.rowGap = rowGap;
		this.columnGap = columnGap;
	}

	public static List<Direction> everyDirection() {
		return Arrays.asList(N, S, E, W, NE, NW, SE, SW);
	}

	public static List<Direction> linearDirection() {
		return Arrays.asList(N, S, E, W);
	}

	public static List<Direction> diagonalDirection() {
		return Arrays.asList(NE, NW, SE, SW);
	}

	public static List<Direction> knightDirection() {
		return Arrays.asList(NNE, NNW, SSE, SSW, NEE, NWW, SEE, SWW);
	}

	public static List<Direction> whitePawnDirection() {
		return Arrays.asList(N, NE, NW);
	}

	public static List<Direction> blackPawnDirection() {
		return Arrays.asList(S, SE, SW);
	}

	public int getRowGap() {
		return rowGap;
	}

	public int getColumnGap() {
		return columnGap;
	}
}
