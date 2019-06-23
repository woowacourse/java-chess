package chess.domain;

import chess.domain.board.Tile;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSameDirection(int columnDiff, int rowDiff) {
        if ((columnDiff * x < 0) || (rowDiff * y < 0)) {
            return false;
        }

        return (x * rowDiff == y * columnDiff);
    }

    public Tile nextTile(Tile current) {
        return current.next(x, y);
    }
}
