package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;

import java.util.List;

public final class Bishop extends Piece {
    private static final char NAME_WHEN_BLACK = 'B';
    private static final char NAME_WHEN_WHITE = 'b';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 3;

    public Bishop(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public Bishop(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    public Bishop(final Color color, final Column column, final int y) {
        super(color, column, Row.row(y));
    }

    public Bishop(final Color color, final Column column, final Row row) {
        super(color, column, row);
    }

    @Override
    public final List<Direction> directions() {
        return Direction.diagonalDirection();
    }

    @Override
    public final int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public final char name() {
        if (color() == Color.BLACK) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }

    @Override
    public final double score() {
        return SCORE;
    }
}