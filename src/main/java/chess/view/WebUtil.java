package chess.view;

import chess.domain.Position;

public class WebUtil {
    public static String positionParser(Position position) {
        return String.valueOf(position.getX2()) + position.getY2();
    }
}
