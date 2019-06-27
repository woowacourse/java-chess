package chess.domain.view;

import chess.domain.position.Position;
import chess.domain.position.Positions;

public class WebInputParser {

    private static final int X = 0;
    private static final int Y = 1;
    private static final char CHAR_TO_INTEGER_UNIT = '0';

    public static Position getSourcePosition(String sourceInput) {
        return Positions.matchWith(getCoordinate(sourceInput, X), getCoordinate(sourceInput, Y));
    }

    private static int getCoordinate(String sourceInput, int coordinate) {
        return (int) sourceInput.charAt(coordinate) - CHAR_TO_INTEGER_UNIT;
    }

    public static Position getTargetPosition(String targetInput) {
        return Positions.matchWith(getCoordinate(targetInput, X), getCoordinate(targetInput, Y));
    }
}
