package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Queen extends Piece {
    private static final char NAME_WHEN_BLACK = 'Q';
    private static final char NAME_WHEN_WHITE = 'q';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 9;

    public Queen(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }

    @Override
    public char name() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
