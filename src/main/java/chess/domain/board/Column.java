package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Column {
	A(0, "a"),
	B(1, "b"),
	C(2, "c"),
	D(3, "d"),
	E(4, "e"),
	F(5, "f"),
	G(6, "g"),
	H(7, "h"),
	;

	private static final String INVALID_COLUMN_EXCEPTION = "존재하지 않는 열입니다.";

	private final int value;
	private final String name;

	Column(final int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static Column from(String rawColumn) {
		return Arrays.stream(values())
			.filter(column -> column.name.equals(rawColumn))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_EXCEPTION));
	}

	private static Column from(int value) {
		return Arrays.stream(values())
			.filter(column -> column.value == value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_EXCEPTION));
	}

	public Column flip() {
		return Arrays.stream(Column.values())
			.filter(it -> it.value == (7 - this.value))
			.findFirst()
			.orElseThrow();
	}

	public int distance(Column otherColumn) {
		return Math.abs(this.value - otherColumn.value);
	}

	public List<Column> pathTo(Column otherColumn) {
		if (this.value < otherColumn.value) {
			return this.upPathTo(otherColumn);
		}
		return this.downPathTo(otherColumn);
	}

	private List<Column> upPathTo(Column otherColumn) {
		List<Column> path = new ArrayList<>();
		for (int i = this.value + 1; i < otherColumn.value; i++) {
			path.add(Column.from(i));
		}
		return path;
	}

	private List<Column> downPathTo(Column otherColumn) {
		List<Column> path = new ArrayList<>();
		for (int i = this.value - 1; i > otherColumn.value; i--) {
			path.add(Column.from(i));
		}
		return path;
	}
}
