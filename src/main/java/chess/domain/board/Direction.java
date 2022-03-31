package chess.domain.board;

import chess.domain.Team;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public enum Direction {

    S(-1, 0),
    W(0, -1),
    N(1, 0),
    E(0, 1),
    SS(-2, 0),
    NN(2, 0),
    SW(-1, -1),
    SE(-1, 1),
    NW(1, -1),
    NE(1, 1),
    NNE(2, 1),
    NNW(2, -1),
    SSE(-2, 1),
    SSW(-2, -1),
    EEN(1, 2),
    EES(-1, 2),
    WWN(1, -2),
    WWS(-1, -2);

    private static final String NOT_SEARCH_DIRECTION_ERROR = "해당 방향이 없습니다.";

    private final int rowMovement;
    private final int columnMovement;

    Direction(int rowMovement, int columnMovement) {
        this.rowMovement = rowMovement;
        this.columnMovement = columnMovement;
    }

    public static Direction find(final int rowMovement, final int columnMovement) {
        return Arrays.stream(values())
                .filter(value -> value.rowMovement == rowMovement && value.columnMovement == columnMovement)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NOT_SEARCH_DIRECTION_ERROR));
    }

    public int addRow(final int row) {
        return row + rowMovement;
    }

    public int addColumn(final int column) {
        return column + columnMovement;
    }
}
