package chess.domain.piece;

import java.util.Arrays;

public enum Color {
	WHITE,
	BLACK,
	NONE;

	public static Color from(char symbol) {
		if (Character.isUpperCase(symbol)) {
			return Color.BLACK;
		}
		if (Character.isLowerCase(symbol)) {
			return Color.WHITE;
		}
		return NONE;
	}

	public static Color from(String name) {
		return Arrays.stream(values())
				.filter(color -> color.name().equals(name))
				.findFirst()
				.orElse(NONE);
	}

	public boolean isWhite() {
		return this == WHITE;
	}

	public boolean isBlack() {
		return this == BLACK;
	}

	public boolean isNone() {
		return this == NONE;
	}
}
