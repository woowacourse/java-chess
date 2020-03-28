package chess.domain.position;

public enum Row {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private final String name;
	private final int row;

	Row(String name, int row) {
		this.name = name;
		this.row = row;
	}

	public String getName() {
		return name;
	}
}
