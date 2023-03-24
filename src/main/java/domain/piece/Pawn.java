package domain.piece;

import domain.position.Position;
import java.util.Objects;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int ONE_STEP = 1;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int TWO_STEP_AT_FIRST = 2;
    private static final char INITIAL_RANK_BLACK = '7';
    private static final char INITIAL_RANK_WHITE = '2';

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (nowInitialPosition(source)) {
            return firstMovable(source, destination);
        }
        if (isBlack()) {
            return source.moveDown(ONE_STEP).equals(destination);
        }
        return source.moveUp(ONE_STEP).equals(destination);
    }

    private boolean firstMovable(final Position source, final Position destination) {
        if (isBlack()) {
            return source.moveDown(TWO_STEP_AT_FIRST).equals(destination) ||
                    source.moveDown(ONE_STEP).equals(destination);
        }
        return source.moveUp(TWO_STEP_AT_FIRST).equals(destination) ||
                source.moveUp(ONE_STEP).equals(destination);
    }

    private boolean nowInitialPosition(final Position source) {
        return (isBlack() && source.getRank() == INITIAL_RANK_BLACK) ||
                (isWhite() && source.getRank() == INITIAL_RANK_WHITE);
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        if (isBlack() &&
                Objects.equals(destination, validateEdgeDestination(source, DOWN, LEFT)) ||
                Objects.equals(destination, validateEdgeDestination(source, DOWN, RIGHT))) {
            return true;
        }

        return !isBlack() &&
                Objects.equals(destination, validateEdgeDestination(source, UP, LEFT)) ||
                Objects.equals(destination, validateEdgeDestination(source, UP, RIGHT));
    }


    private Position validateEdgeDestination(final Position source, final int rank, final int file) {
        try {
            return source.move(rank, file);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
