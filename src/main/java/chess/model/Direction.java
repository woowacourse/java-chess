package chess.model;

public enum Direction {
    E,
    NE,
    N,
    NW,
    W,
    SW,
    S,
    SE,
    UNDEFINED;

    public static Direction valueOf (Point source, Point target) {
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
}
