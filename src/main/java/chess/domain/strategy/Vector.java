package chess.domain.strategy;

import chess.domain.board.Position;
import java.util.List;

public enum Vector {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1),
    ;


    final int col;
    final int row;

    Vector(final int col, final int row) {
        this.col = col;
        this.row = row;
    }

    public boolean isSameDirection(final Position source, final Position destination) {
        List<Integer> moveDirection = generateVector(source, destination);
        return this.col == moveDirection.get(0) && this.row == moveDirection.get(1);
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
