package chess.domain.piece;

import chess.domain.Point;

public final class Bishop extends MultiMovePiece {

    private static final String name = "B";

    public Bishop(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovableDirection(final Point departure, final Point destination) {
        return departure.isDiagonalWithSlopeOfOne(destination);
    }
}
