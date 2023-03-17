package domain.piece;

import domain.position.Position;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int ONE_STEP = 1;
    private static final int TWO_STEP_AT_FIRST = 2;
    private static final char INITIAL_RANK_BLACK = '7';
    private static final char INITIAL_RANK_WHITE = '2';

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (isBlack() && source.getRank() == INITIAL_RANK_BLACK) {
            return source.moveDown(TWO_STEP_AT_FIRST).equals(destination) ||
                    source.moveDown(ONE_STEP).equals(destination);
        }
        if (!isBlack() && source.getRank() == INITIAL_RANK_WHITE) {
            return source.moveUp(TWO_STEP_AT_FIRST).equals(destination) ||
                    source.moveUp(ONE_STEP).equals(destination);
        }
        if (isBlack()) {
            return source.moveDown(ONE_STEP).equals(destination);
        }
        return source.moveUp(ONE_STEP).equals(destination);
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        if (isBlack() &&
                source.moveDownLeft(ONE_STEP).equals(destination) ||
                source.moveDownRight(ONE_STEP).equals(destination)) {
            return true;
        }

        return !isBlack() &&
                source.moveUpLeft(ONE_STEP).equals(destination) ||
                source.moveUpRight(ONE_STEP).equals(destination);
    }
}
