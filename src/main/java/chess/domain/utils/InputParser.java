package chess.domain.utils;

import chess.domain.Coordinate;
import chess.domain.Position;

public class InputParser {
    // TODO: 아름다운 코드로 바꿔야 합니다.
    public static Position position(String input) {
        return new Position(new Coordinate(input.charAt(0)), new Coordinate(Integer.parseInt(input.substring(1))));
    }
}
