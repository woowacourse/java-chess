package chess.domain.piece;

import java.util.stream.Stream;

public enum Color {
	WHITE,
	BLACK;

	public static Color of(String input) {
		return Stream.of(values())
				.filter(color -> color.isSameName(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색상입니다."));
	}

	public Color reverse() {
		if (this.equals(WHITE)) {
			return BLACK;
		}
		return WHITE;
	}

	private boolean isSameName(String input) {
		return name().equals(input.toUpperCase());
	}
}
