package chess.domain.piece;

import java.util.Arrays;

public enum Color {
	WHITE("1"),
	BLACK("8");

	private final String initialRow;

	Color(String initialRow) {
		this.initialRow = initialRow;
	}

	public static Color of(String color) {
		return Arrays.stream(Color.values())
			.filter(x -> x.name().equals(color))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("그런색은 없습니다."));
	}

	public String getInitialRow() {
		return initialRow;
	}

	public Color reverse() {
		if (this == WHITE) {

			return BLACK;
		}
		return WHITE;
	}
}
