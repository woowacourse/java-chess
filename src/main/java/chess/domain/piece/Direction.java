package chess.domain.piece;

import java.util.Arrays;

import chess.domain.board.Column;
import chess.domain.board.Point;
import chess.domain.board.Row;

public enum Direction {
	NORTH(-1, 0),
	NORTH_EAST(-1, 1),
	EAST(0, 1),
	SOUTH_EAST(1, 1),
	SOUTH(1, 0),
	SOUTH_WEST(1, -1),
	WEST(0, -1),
	NORTH_WEST(-1, -1);

	private final Row rowDirection;
	private final Column columnDirection;

	Direction(int rowDirection, int columnDirection) {
		this.rowDirection = new Row(rowDirection);
		this.columnDirection = new Column(columnDirection);
	}

	public static Direction findDirection(Point source, Point target) {
		int initialRowDifference = target.subtractRow(source);
		int initialColumnDifference = target.subtractColumn(source);

		if (initialRowDifference == 0 && initialColumnDifference == 0) {
			throw new IllegalArgumentException("기물이 움직이지 않습니다.");
		}

		if ((Math.abs(initialRowDifference) != Math.abs(initialColumnDifference))
			&& initialRowDifference != 0 && initialColumnDifference != 0) {
			throw new IllegalArgumentException("기물이 움직일 수 없는 방향입니다.");
		}

		return Arrays.stream(Direction.values())
			.filter(direction -> direction.rowDirection.isSameAs(normalizeDifference(initialRowDifference))
				&& direction.columnDirection.isSameAs(normalizeDifference(initialColumnDifference)))
			.findAny().orElseThrow(RuntimeException::new);
	}

	private static int normalizeDifference(int difference) {
		return Integer.compare(difference, 0);
	}

	public int addCurrentRow(Row row) {
		return rowDirection.add(row);
	}

	public int addCurrentColumn(Column column) {
		return columnDirection.add(column);
	}
}
