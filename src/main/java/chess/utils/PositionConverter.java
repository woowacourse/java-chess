package chess.utils;

import chess.domain.Position;
import chess.exception.PositionConvertException;

public class PositionConverter {
	private static final String DELIMITER = "";

	public static Position convert(final String input) {
		String[] position = input.split(DELIMITER);
		if (position.length != 2) {
			throw new IllegalArgumentException();
		}
		String col = position[0];
		String row = position[1];
		return Position.of(row, col);
	}
}
