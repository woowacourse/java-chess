package chess.utils;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.utils.exceptions.InvalidPositionInputExecption;

public class PositionParser {
    public static Position parse(String input) {
        validInput(input);
        return new Position(new Coordinate(input.charAt(0)), new Coordinate(Integer.parseInt(input.substring(1))));
    }

    private static void validInput(String input) {
        if (!input.matches("[a-h][1-8]")) {
            throw new InvalidPositionInputExecption("유효한 형식이 아닙니다.");
        }
    }
}
