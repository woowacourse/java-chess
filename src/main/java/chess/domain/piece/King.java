package chess.domain.piece;

import chess.domain.Point;

public final class King extends SingleMovePiece {

    private static final String name = "K";

    public King(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovablePoint(final Point departure, final Point destination) {
        return departure.isAround(destination);
    }
}
