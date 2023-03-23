package chess.controller;

import chess.domain.Position;

public class InputRenderer {

	private static final String INVALID_COMMAND_ERROR_MESSAGE = "불가능한 명령입니다.";
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private static final char INITIAL_FILE = 'a';
	private static final char INITIAL_RANK = '1';

	public static Command toCommand(final String string) {
		try {
			return Command.valueOf(string.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(INVALID_COMMAND_ERROR_MESSAGE);
		}
	}

	public static PositionDto toPosition(final String rawPosition1, final String rawPosition2) {
		Position source = new Position(getColumn(rawPosition1), getRow(rawPosition1));
		Position target = new Position(getColumn(rawPosition2), getRow(rawPosition2));
		return new PositionDto(source, target);
	}

	private static int getColumn(String rawPosition) {
		char file = rawPosition.toCharArray()[COLUMN_INDEX];
		return file - INITIAL_FILE;
	}

	private static int getRow(String rawPosition) {
		char rank = rawPosition.toCharArray()[ROW_INDEX];
		return rank - INITIAL_RANK;
	}
}
