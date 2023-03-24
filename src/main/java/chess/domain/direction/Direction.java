package chess.domain.direction;

import chess.domain.board.Position;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1),

    NORTH_NORTH_WEST(-1, 2),
    NORTH_NORTH_EAST(1, 2),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1),
    SOUTH_SOUTH_WEST(-1, -2),
    SOUTH_SOUTH_EAST(1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1);

    final int col;
    final int row;

    Direction(final int col, final int row) {
        this.col = col;
        this.row = row;
    }


    public boolean isSameDirection(final Position source, final Position destination) {
        List<Integer> moveDirection = generateVector(source, destination);
        return this.col == moveDirection.get(0) && this.row == moveDirection.get(1);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    private List<Integer> generateVector(final Position source, final Position destination) {
        int subCol = destination.calculateDistanceOfCol(source);
        int subRow = destination.calculateDistanceOfRow(source);
        int divideNumber = generateDivideNumber(subCol, subRow);

        int colOfDirection = subCol / divideNumber + subCol % divideNumber;
        int rowOfDirection = subRow / divideNumber + subRow % divideNumber;
        return List.of(colOfDirection, rowOfDirection);
    }

    private int generateDivideNumber(int subCol, int subRow) {
        if (subCol == 0) {
            return Math.abs(subRow);
        }

        if (subRow == 0) {
            return Math.abs(subCol);
        }
        return Math.min(Math.abs(subCol), Math.abs(subRow));
    }
}
