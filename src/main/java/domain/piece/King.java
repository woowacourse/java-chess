package domain.piece;

import domain.position.Position;
import java.util.Map;

public final class King extends Piece {

    private static final String NAME = "K";
    private static final int DIAGONAL_ONE_STEP = 2;
    private static final int ONE_STEP = 1;

    public King(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (source.isDiagonal(destination) && source.getDistance(destination) == DIAGONAL_ONE_STEP) {
            return true;
        }

        return source.isStraight(destination) && source.getDistance(destination) == ONE_STEP;
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        return isMovable(source, destination);
    }

    @Override
    public boolean isEndGameIfDead() {
        return true;
    }

    @Override
    public Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore) {
        return new Score(0);
    }
}
