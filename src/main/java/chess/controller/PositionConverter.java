package chess.controller;

import chess.domain.Position;

public class PositionConverter {
    public static Position convert(final String position) {
        char[] columnAndRow = position.toCharArray();
        int column = convertColumn(columnAndRow[0]);
        int row = columnAndRow[1] - '0';

        return new Position(row, column);
    }

    private static int convertColumn(final char rawColumn) {
        return rawColumn - 'a' + 1;
    }
}
