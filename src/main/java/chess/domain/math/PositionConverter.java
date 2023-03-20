package chess.domain.math;

import chess.domain.Position;

public class PositionConverter {

    private static final int COLUMN_INDEX = 0;
    private static final int ASCII_SMALL_A = 97;
    private static final int ROW_INDEX = 1;
    private static final int BOARD_MAX_ROW = 8;

    public static Position toPosition(final String input) {
        char columnValue = input.charAt(COLUMN_INDEX);
        int column = Math.abs(columnValue - ASCII_SMALL_A);

        char rowValue = input.charAt(ROW_INDEX);
        int row = Math.abs(Character.getNumericValue(rowValue) - BOARD_MAX_ROW);

        return new Position(row, column);
    }
}
