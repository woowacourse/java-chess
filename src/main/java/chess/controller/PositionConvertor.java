package chess.controller;

import chess.domain.board.Position;

public class PositionConvertor {

	private static final String WRONG_LENGTH_ERROR = "움직일 기물의 위치는 2글자로 입력해야 합니다.";
	private static final String WRONG_FORMAT_ERROR = "움직일 기물의 위치는 [알파벳 숫지]로 입력해야 합니다.";
	private static final int FORMAT_LENGTH = 2;
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private static final char NUMBER_START_CHAR = '0';
	private static final char ALPHABET_START_CHAR = 'a';

	public static Position to(final String rawPosition) {
		validateLength(rawPosition);
		validateFormat(rawPosition);
		int row = rawPosition.charAt(ROW_INDEX) - NUMBER_START_CHAR;
		int col = rawPosition.charAt(COLUMN_INDEX) - ALPHABET_START_CHAR + 1;
		return Position.of(row, col);
	}

	private static void validateLength(final String input) {
		if (input.length() != FORMAT_LENGTH) {
			throw new IllegalArgumentException(WRONG_LENGTH_ERROR);
		}
	}

	private static void validateFormat(final String input) {
		if (!Character.isAlphabetic(input.charAt(COLUMN_INDEX)) || !Character.isDigit(
				input.charAt(ROW_INDEX))) {
			throw new IllegalArgumentException(WRONG_FORMAT_ERROR);
		}
	}
}
