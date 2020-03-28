package chess.domain.util;

import chess.domain.position.Position;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NE(1, 1),
    NW(-1, 1),
    SE(1, -1),
    SW(-1, -1),
    SSE(1, -2),
    SSW(-1, -2),
    NNE(1, 2),
    NNW(-1, 2),
    EES(2, -1),
    EEN(2, 1),
    WWS(-2, -1),
    WWN(-2, 1);

    private final int col;
    private final int row;

    Direction(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public Position move(Position currentPosition) {
        return new Position(currentPosition.getX() + col, currentPosition.getY() + row);
    }


}
