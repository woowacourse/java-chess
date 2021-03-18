package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Queen extends Piece {
    private static final char NAME_WHEN_BLACK = 'Q';
    private static final char NAME_WHEN_WHITE = 'q';
    private static final int STEP_RANGE = 8;

    public Queen(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    @Override
    public int getStepRange(){
        return STEP_RANGE;
    }

    @Override
    public List<Direction> getDirections(){
        return Direction.everyDirection();
    }

    @Override
    public char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
