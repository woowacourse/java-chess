package chess.domain.piece;

import chess.domain.Point;

public final class Knight extends SingleMovePiece {

    private static final String name = "N";

    public Knight(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovablePoint(final Point departure, final Point destination) {
        return Math.abs(departure.multiplyAxis(destination)) == 2;
    }
}
