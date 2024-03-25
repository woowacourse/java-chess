package chess.domain.piece;

import chess.domain.Point;

public final class Queen extends MultiMovePiece {

    private static final String name = "Q";

    public Queen(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovableDirection(final Point departure, final Point destination) {
        return departure.isStraight(destination) || departure.isDiagonalWithSlopeOfOne(destination);
    }
}
