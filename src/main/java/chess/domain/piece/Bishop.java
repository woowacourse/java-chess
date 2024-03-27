package chess.domain.piece;

import chess.domain.point.Point;

public final class Bishop extends MultiMovePiece {

    public Bishop(final Team team) {
        super(Type.BISHOP, team);
    }

    @Override
    public boolean isMovableDirection(final Point departure, final Point destination) {
        return departure.isDiagonalWithSlopeOfOne(destination);
    }
}
