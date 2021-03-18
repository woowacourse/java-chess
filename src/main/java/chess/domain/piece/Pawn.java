package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Pawn extends Piece {
    private static final char NAME_WHEN_BLACK = 'P';
    private static final char NAME_WHEN_WHITE = 'p';
    private static final int STEP_RANGE = 1;

    private boolean moved = false;

    public Pawn(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    public List<Direction> getDirectionsOnTwoStep() {
        if (isBlack()) {
            return Direction.blackPawnLinearDirection();
        }
        return Direction.whitePawnLinearDirection();
    }

    public boolean hasMoved(){
        return moved;
    }

    public void moved(){
        moved = true;
    }

    @Override
    public List<Direction> getDirections() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
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