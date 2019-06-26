package chess.model;

import chess.model.coordinate.Point;

public enum Direction {
    E(1, 0),
    NE(1, 1),
    N(0, 1),
    NW(-1, 1),
    W(-1, 0),
    SW(-1, -1),
    S(0, -1),
    SE(1, -1),
    UNDEFINED(0, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction valueOf(Point source, Point target) {
        String result = "";

        if (source.calculateYsSub(target) < 0) {
            result += "N";
        }

        if (source.calculateYsSub(target) > 0) {
            result += "S";
        }

        if (source.calculateXsSub(target) < 0) {
            result += "E";
        }

        if (source.calculateXsSub(target) > 0) {
            result += "W";
        }

        if (result.equals("NE") || result.equals("NW") || result.equals("SE") || result.equals("SW")) {
            if (source.calculateXsDiff(target) != source.calculateYsDiff(target)) {
                return Direction.UNDEFINED;
            }
        }

        return Direction.valueOf(result);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
