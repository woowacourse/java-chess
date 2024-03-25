package chess.view;

import chess.domain.Position;

import java.util.regex.Pattern;

public class PositionConverter {
    public static final int ROW_INDEX = 1;
    public static final int COLUMN_INDEX = 0;
    private static final String REGEX = "^[a-h][1-8]$";

    private PositionConverter() {
    }

    public static Position generate(String value) {
        if (!Pattern.matches(REGEX, value)) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }

        int row = value.charAt(ROW_INDEX) - '0';
        int column = value.charAt(COLUMN_INDEX) - 'a' + 1;

        return Position.of(row, column);
    }
}
