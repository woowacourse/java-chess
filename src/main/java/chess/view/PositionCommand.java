package chess.view;

import chess.domain.Position;
import chess.exception.InvalidCommandException;
import java.util.regex.Pattern;

public class PositionCommand {
    private static final String REGEX = "^[a-h][1-8]$";
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;

    private PositionCommand() {
    }

    public static Position generate(String value) {
        if (!Pattern.matches(REGEX, value)) {
            throw new InvalidCommandException("잘못된 위치 입력입니다.");
        }

        int row = value.charAt(ROW_INDEX) - '0';
        int column = value.charAt(COLUMN_INDEX) - 'a' + 1;

        return Position.of(row, column);
    }
}
