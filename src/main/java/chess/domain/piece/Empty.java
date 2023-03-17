package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

public final class Empty extends Piece {

    public Empty(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        return ".";
    }

    @Override
    public boolean movable(final Direction direction) {
        return false;
    }

    @Override
    public boolean movableByCount(final int count) {
        return false;
    }
}
