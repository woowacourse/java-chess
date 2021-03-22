package chess.controller.converter;

import chess.domain.Position;

public final class StringPositionConverter {

    private StringPositionConverter() {}

    public static Position convert(String message) {
        char[] rowAndColumn = message.toCharArray();
        int x = rowAndColumn[0] - 'a';
        int y = rowAndColumn[1] - '0';
        return Position.of(x, y);
    }
}
