package chess.domain.utils;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.exceptions.ChessPlayException;

import java.util.regex.Pattern;

public class InputParser {
    private static final Pattern INPUT_VALID_REGEX = Pattern.compile("[a-z][0-9]");

    public static Position position(String input) {
        if (!INPUT_VALID_REGEX.matcher(input).matches()) {
            throw new ChessPlayException("입력값 형식이 맞지 않습니다.");
        }

        return new Position(new Coordinate(input.charAt(0)), new Coordinate(Integer.parseInt(input.substring(1))));
    }
}
