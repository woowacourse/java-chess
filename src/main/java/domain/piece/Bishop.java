package domain.piece;

import domain.position.Position;
import java.util.Map;
import java.util.Optional;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop(Team team) {
        super(NAME, team);
    }

    @Override
    public Optional<Position> move(final Position source, final Position destination) {
        if (source.isDiagonal(destination)) {
            return Optional.of(destination);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Position> eat(final Position source, final Position destination) {
        return move(source, destination);
    }

    @Override
    public Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore) {
        return new Score(3);
    }
}
