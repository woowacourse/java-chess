package chess.view;

import chess.domain.Position;

public class WebUtil {
    public static String positionParser(Position position) {
        return parseCoordinateX(position.getXCoordinate()) + position.getYCoordinate();
    }

    private static String parseCoordinateX(int x) {
        return String.valueOf((char)(x + 96));
    }
}
