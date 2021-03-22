package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;

import java.util.List;

public final class Knight extends Piece {
    private static final char NAME_WHEN_BLACK = 'N';
    private static final char NAME_WHEN_WHITE = 'n';
    private static final int STEP_RANGE = 1;
    private static final double SCORE = 2.5;

    public Knight(final Color color, final Column column, final Row row) {
        super(color, column.getName(), row.getName());
    }

    public Knight(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public Knight(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    @Override
    public final List<Direction> directions() {
        return Direction.knightDirection();
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

