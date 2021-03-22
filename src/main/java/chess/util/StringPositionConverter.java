package chess.util;

import chess.domain.Position;

public class StringPositionConverter {

    public static final int MIN_INDEX = 1;

    public StringPositionConverter() {
    }

    public Position convert(String message) {
        char[] rowAndColumn = message.toCharArray();
        int x = rowAndColumn[0] - 'a';
        int y = rowAndColumn[1] - '0' - MIN_INDEX;
        return Position.of(x, y);
    }
}
