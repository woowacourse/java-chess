package chess.domain.piece;

import chess.domain.move.enums.MoveEnum;
import chess.domain.team.Team;

public final class Empty extends Piece {

    public Empty(final Team team) {
        super(team);
    }
    @Override
    public boolean movable(final MoveEnum move) {
        return false;
    }

    @Override
    public boolean movableByCount(final int count) {
        return false;
    }
}
