package chess.position;

import java.util.List;

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

	public static List<File> valuesBetween(File start, File end) {
		return null;
	}

	public String getName() {
		return name;
	}
}
