package chess.domain.board;

import java.util.Objects;

public class Row {
	private final int row;

	public Row(int row) {
		this.row = row;
	}

	public int subtract(Row anotherRow) {
		return row - anotherRow.row;
	}

	public int add(Row anotherRow) {
		return this.row + anotherRow.row;
	}

	public boolean isSameAs(int row) {
		return this.row == row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Row row1 = (Row)o;
		return row == row1.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row);
	}
}
