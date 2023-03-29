package domain.piece;

import domain.position.Position;
import java.util.Map;
import java.util.Optional;

public final class King extends Piece {

    private static final String NAME = "K";
    private static final int DIAGONAL_ONE_STEP = 2;
    private static final int ONE_STEP = 1;

    public King(Team team) {
        super(NAME, team);
    }

    @Override
    public Optional<Position> move(final Position source, final Position destination) {
        if (source.isDiagonal(destination) && source.getDistance(destination) == DIAGONAL_ONE_STEP) {
            return Optional.of(destination);
        }
        if (source.isStraight(destination) && source.getDistance(destination) == ONE_STEP) {
            return Optional.of(destination);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Position> eat(final Position source, final Position destination) {
        return move(source, destination);
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
