package chess.domain.piece;

import chess.domain.point.Point;

public final class Knight extends SingleMovePiece {

    public Knight(final Team team) {
        super(Type.KNIGHT, team);
    }

    @Override
    public boolean isMovablePoint(final Point departure, final Point destination) {
        return Math.abs(departure.multiplyAxis(destination)) == 2;
    }
}
