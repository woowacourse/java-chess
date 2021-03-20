package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public final class Rook extends Piece {
    private static final char NAME_WHEN_BLACK = 'R';
    private static final char NAME_WHEN_WHITE = 'r';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 5;

    public Rook(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public Rook(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    @Override
    public final List<Direction> directions() {
        return Direction.linearDirection();
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
    public final char name() {
        if (color() == Color.BLACK) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}