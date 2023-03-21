package chess.domain.piece;

import chess.domain.team.Team;

public final class Empty extends Piece {

    public Empty(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move, final Piece targetPiece) {
        return false;
    }

    @Override
    public boolean movableByCount(final int count) {
        return false;
    }
}
