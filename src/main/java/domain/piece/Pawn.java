package domain.piece;

import domain.position.Direction;
import domain.position.Position;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int TWO_STEP = 2;
    private static final char INITIAL_RANK_BLACK = '7';
    private static final char INITIAL_RANK_WHITE = '2';

    public Pawn(final Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        final Direction direction = Direction.of(source, destination);
        final int distance = source.getDistance(destination);

        if (isBlack() && isBlackInitialRank(source)) {
            return Direction.S.equals(direction) && distance <= TWO_STEP;
        }
        if (isBlack()) {
            return source.move(Direction.S).equals(destination);
        }
        if (isWhiteInitialRank(source)) {
            return Direction.N.equals(direction) && distance <= TWO_STEP;
        }
        return source.move(Direction.N).equals(destination);
    }

    private boolean isWhiteInitialRank(final Position source) {
        return source.getRank() == INITIAL_RANK_WHITE;
    }

    private boolean isBlackInitialRank(final Position source) {
        return source.getRank() == INITIAL_RANK_BLACK;
    }

    @Override
    public boolean isCapturable(final Position source, final Position destination) {
        if (isBlack() &&
                source.move(Direction.SW).equals(destination) ||
                source.move(Direction.SE).equals(destination)) {
            return true;
        }

        return !isBlack() &&
                source.move(Direction.NW).equals(destination) ||
                source.move(Direction.NE).equals(destination);
    }
}
