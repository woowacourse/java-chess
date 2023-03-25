package domain.piece;

import domain.position.Position;
import java.util.Map;

public final class Knight extends Piece {

    private static final String NAME = "N";
    private static final int ONE_STEP_OF_KNIGHT = 3;

    public Knight(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.getDistance(destination) == ONE_STEP_OF_KNIGHT &&
                !source.isStraight(destination) &&
                !source.isDiagonal(destination);
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        return isMovable(source, destination);
    }

    @Override
    public Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore) {
        return new Score(2.5);
    }
}
