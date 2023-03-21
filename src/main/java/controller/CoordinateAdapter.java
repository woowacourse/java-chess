package controller;

import domain.piece.move.Coordinate;

public final class CoordinateAdapter {

    private static final char ASCII_ALPHABET_A = 'a';
    public static final int COMMAND_SIZE = 2;

    public static Coordinate convert(final String frontCoordinate) {
        validateSize(frontCoordinate);
        int row = convertToRow(frontCoordinate);
        int col = convertToCol(frontCoordinate);
        return new Coordinate(row, col);
    }

    private static void validateSize(final String frontCoordinate) {
        if (frontCoordinate.length() != COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 움직임 명령입니다.");
        }
    }

    private static int convertToRow(final String frontCoordinate) {
        char pureRow = frontCoordinate.charAt(1);
        if (pureRow >='0' && pureRow <= '9') {
            return Character.getNumericValue(pureRow) - 1;
        }
        throw new IllegalArgumentException("[ERROR] Y축 좌표는 숫자여야 합니다.");
    }

    private static int convertToCol(final String frontCoordinate) {
        char pureCol = frontCoordinate.charAt(0);
        if (Character.isAlphabetic(pureCol) &&
                Character.isLowerCase(pureCol)) {
            return (int) pureCol - ASCII_ALPHABET_A;
        }
        throw new IllegalArgumentException("[ERROR] X축 좌표는 알파벳 소문자여야 합니다.");
    }
}
