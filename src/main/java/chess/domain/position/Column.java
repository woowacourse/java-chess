package chess.domain.position;

import java.util.Arrays;

public enum Column {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private final String name;
	private final int position;

	Column(String name, int position) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public Column plus() {
		return Arrays.stream(Column.values())
			.filter(value -> value.position == this.position + 1)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("9 이상의 column 값은 가질수 없습니다."));
	}

	public Column minus() {
		return Arrays.stream(Column.values())
			.filter(value -> value.position == this.position - 1)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("0 이하의 column 값은 가질수 없습니다."));
	}

	public Column reverse() {
		return Arrays.stream(Column.values())
			.filter(value -> value.position == 9 - this.position)
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}
}
