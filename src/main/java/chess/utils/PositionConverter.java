package chess.utils;

import chess.domain.Position;

public class PositionConverter {

	public static final String DELIMITER = "";

	public static Position convert(final String input) {
		String[] position = input.split(DELIMITER);
		String col = position[0];
		String row = position[1];
		return Position.of(row, col);
	}
}
