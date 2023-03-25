package domain.piece;

import domain.position.Position;
import java.util.Map;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.isDiagonal(destination);
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        return isMovable(source, destination);
    }

    @Override
    public Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore) {
        return new Score(3);
    }
}
