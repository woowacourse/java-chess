package chess.model.square;

import java.util.List;

public enum Direction {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    SOUTH_EAST(1, -1),
    NORTH_EAST(1, 1),
    SOUTH_WEST(-1, -1),
    NORTH_WEST(-1, 1),
    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public List<Integer> getDistanceFrom(int distance) {
        return List.of(row * distance, col * distance);
    }
}
