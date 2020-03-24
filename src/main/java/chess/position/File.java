package chess.position;

public enum File {
	A("a"),
	B("b"),
	C("c"),
	D("d"),
	E("e"),
	F("f"),
	G("g"),
	H("h");

	private final String name;

	File(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
