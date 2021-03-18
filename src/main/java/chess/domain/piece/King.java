package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class King extends Piece {
    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';
    private static final int  STEP_RANGE= 1;

    public King(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.everyDirection();
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
