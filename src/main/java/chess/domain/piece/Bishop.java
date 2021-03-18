package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Bishop extends Piece {
    private static final char NAME_WHEN_BLACK = 'B';
    private static final char NAME_WHEN_WHITE = 'b';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 3;

    public Bishop(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.diagonalDirection();
    }

    @Override
    public int getStepRange() {
        return STEP_RANGE;
    }

    @Override
    public char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}