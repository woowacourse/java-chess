package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Rook extends Piece {
    private static final char NAME_WHEN_BLACK = 'R';
    private static final char NAME_WHEN_WHITE = 'r';
    private static final int STEP_RANGE = 8;

    public Rook(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.linearDirection();
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
}