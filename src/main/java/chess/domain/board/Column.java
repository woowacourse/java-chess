package chess.domain.board;

import java.util.Objects;

public class Column {
	private final int column;

	public Column(int column) {
		this.column = column;
	}

	public boolean isSameAs(int anotherColumn) {
		return column == anotherColumn;
	}

	public int subtract(Column anotherColumn) {
		return this.column - anotherColumn.column;
	}

	public int add(Column anotherColumn) {
		return this.column + anotherColumn.column;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Column column1 = (Column)o;
		return column == column1.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column);
	}
}
