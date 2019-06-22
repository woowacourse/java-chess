package chess.utils;

import chess.domain.Coordinate;
import chess.domain.Position;

public class QueryParser {
    public static Position position(String x) {
        return new Position(new Coordinate(x.charAt(0)), new Coordinate(Integer.parseInt(x.substring(1))));
    }
}
