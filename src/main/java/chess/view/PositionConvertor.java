package chess.view;

import chess.domain.board.Position;

public class PositionConvertor {

    private static final String CONVERT_ERROR = "올바르지 않은 좌표 입력입니다.";

    public static Position to(final String rawPosition) {
        if (Character.isDigit(rawPosition.charAt(1)) && Character.isAlphabetic(rawPosition.charAt(0))) {
            int row = rawPosition.charAt(1) - '0';
            int col = rawPosition.charAt(0) - 'a' + 1;
            return Position.of(row, col);
        }
        throw new IllegalArgumentException(CONVERT_ERROR);
    }
}
