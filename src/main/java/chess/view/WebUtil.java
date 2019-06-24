package chess.view;

import chess.domain.Coordinate;
import chess.domain.Position;

public class WebUtil {
    public static String positionParser(Position position) {
        return parseCoordinateX(position.getX()) + position.getY();
    }

    private static String parseCoordinateX(Coordinate x) {
        return String.valueOf((char)(x.getCoordinate() + 96));
    }
}
