package chess.view;

import chess.domain.board.Position;

public class PositionConvertor {

	private static final String CONVERT_ERROR = "올바르지 않은 좌표 입력입니다.";
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private static final char NUMBER_START_CHAR = '0';
	private static final char ALPHABET_START_CHAR = 'a';

	public static Position to(final String rawPosition) {
		if (Character.isAlphabetic(rawPosition.charAt(COLUMN_INDEX)) && Character.isDigit(
				rawPosition.charAt(ROW_INDEX))) {
			int row = rawPosition.charAt(ROW_INDEX) - NUMBER_START_CHAR;
			int col = rawPosition.charAt(COLUMN_INDEX) - ALPHABET_START_CHAR + 1;
			return Position.of(row, col);
		}
		throw new IllegalArgumentException(CONVERT_ERROR);
	}
}
