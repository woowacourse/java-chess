package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class King extends Piece {
    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';
    private static final int STEP_RANGE = 1;
    private static final int SCORE = 0;

    public King(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }

    @Override
    public int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public char name() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
