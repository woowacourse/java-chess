package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;

import java.util.List;

public final class Queen extends Piece {
    private static final char NAME_WHEN_BLACK = 'Q';
    private static final char NAME_WHEN_WHITE = 'q';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 9;

    public Queen(final Color color, final Column column, final Row row) {
        super(color, column.getName(), row.getName());
    }

    public Queen(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public Queen(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    @Override
    public final int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public final double score() {
        return SCORE;
    }

    @Override
    public final List<Direction> directions() {
        return Direction.everyDirection();
    }

    @Override
    public final char name() {
        if (color() == Color.BLACK) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
