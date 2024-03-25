package chess.domain.piece;

import chess.domain.Point;

public final class Rook extends MultiMovePiece {

    private static final String name = "R";

    public Rook(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovableDirection(final Point departure, final Point destination) {
        return departure.isStraight(destination);
    }
}
