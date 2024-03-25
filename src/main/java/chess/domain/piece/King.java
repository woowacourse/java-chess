package chess.domain.piece;

import chess.domain.Point;

public final class King extends SingleMovePiece {

    public King(Team team) {
        super(Type.KING, team);
    }

    @Override
    public boolean isMovablePoint(final Point departure, final Point destination) {
        return departure.isAround(destination);
    }
}
