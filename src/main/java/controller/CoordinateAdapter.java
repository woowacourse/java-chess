package controller;

import domain.piece.Coordinate;

public class CoordinateAdapter {

    private static final char ASCII_ALPHABET_A = 'a';
    public static final int COMMAND_SIZE = 2;

    public static Coordinate convert(final String frontCoordinate) {
        validateSize(frontCoordinate);
        int row = convertRow(frontCoordinate);
        int col = convertCol(frontCoordinate);
        return new Coordinate(row, col);
    }

    private static void validateSize(final String frontCoordinate) {
        if (frontCoordinate.length() != COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 움직임 명령입니다.");
        }
    }

    private static int convertRow(final String frontCoordinate) {
        char pureRow = frontCoordinate.charAt(1);
        if (pureRow >='0' && pureRow <= '9') {
            return Character.getNumericValue(pureRow) - 1;
        }
        throw new IllegalArgumentException("[ERROR] 행은 알파벳 소문자여야 합니다.");
    }

    private static int convertCol(final String frontCoordinate) {
        char pureCol = frontCoordinate.charAt(0);
        if (Character.isAlphabetic(pureCol) &&
                Character.isLowerCase(pureCol)) {
            return (int) pureCol - ASCII_ALPHABET_A;
        }
        throw new IllegalArgumentException("[ERROR] 열은 숫자여야 합니다.");
    }
}
