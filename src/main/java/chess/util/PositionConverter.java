package chess.util;

import chess.domain.Position;
import chess.exception.InvalidCommandException;
import java.util.regex.Pattern;

public class PositionConverter {
    private static final String REGEX = "^[a-h][1-8]$";
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;

    private PositionConverter() {
    }

    public static Position toPosition(String notation) {
        if (!Pattern.matches(REGEX, notation)) {
            throw new InvalidCommandException("잘못된 위치 입력입니다.");
        }

        int row = notation.charAt(ROW_INDEX) - '0';
        int column = notation.charAt(COLUMN_INDEX) - 'a' + 1;

        return Position.of(row, column);
    }

    public static String toNotation(Position position) {
        char file = (char) ('a' + position.column() - 1);

        return String.valueOf(file) + position.row();
    }
}
