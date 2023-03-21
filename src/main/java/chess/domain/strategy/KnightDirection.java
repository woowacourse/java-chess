package chess.domain.strategy;

import chess.domain.board.Position;
import java.util.List;

public enum KnightDirection {

    KNIGHT_UP_RIGHT(2, 1),
    KNIGHT_UP_LEFT(-2, 1),
    KNIGHT_RIGHT_UP(1, 2),
    KNIGHT_RIGHT_DOWN(1, -2),
    KNIGHT_DOWN_RIGHT(2, -1),
    KNIGHT_DOWN_LEFT(-2, -1),
    KNIGHT_LEFT_UP(-1, 2),
    KNIGHT_LEFT_DOWN(-1, -2),
    ;

    final int col;
    final int row;

    KnightDirection(final int col, final int row) {
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
        return List.of(subCol, subRow);
    }
}
