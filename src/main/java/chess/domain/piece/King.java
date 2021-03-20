package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public final class King extends Piece {
    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';
    private static final int STEP_RANGE = 1;
    private static final int SCORE = 0;

    public King(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public King(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    @Override
    public final List<Direction> directions() {
        return Direction.everyDirection();
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
