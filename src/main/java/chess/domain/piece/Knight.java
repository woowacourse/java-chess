package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Knight extends Piece {
    private static final char NAME_WHEN_BLACK = 'N';
    private static final char NAME_WHEN_WHITE = 'n';
    private static final int STEP_RANGE = 1;
    private static final double SCORE = 2.5;

    public Knight(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.knightDirection();
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

