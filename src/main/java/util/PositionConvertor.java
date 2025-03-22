package util;

import chess.Column;
import chess.Position;
import chess.Row;

public class PositionConvertor {

    public static Position convertInputToPosition(String input) {
        validateLength(input);
        char colInput = getColInput(input);
        char rowInput = getRowInput(input);
        return new Position(Row.findRow(rowInput), Column.findColumn(colInput));
    }

    private static char getColInput(String input) {
        return input.charAt(0);
    }

    private static char getRowInput(String input) {
        return input.charAt(1);
    }

    private static void validateLength(String input) {
        if (input.length() != 2) {
            throw new IllegalArgumentException("열과 행 정보를 정확히 입력해주세요");
        }
    }
}
