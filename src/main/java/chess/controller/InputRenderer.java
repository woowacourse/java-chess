package chess.controller;

import chess.domain.Position;

public class InputRenderer {

	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;
	private static final char INITIAL_FILE = 'a';
	private static final char INITIAL_RANK = '1';

	public static Position toPosition(final String rawPosition) {
		return new Position(getColumn(rawPosition), getRow(rawPosition));
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
