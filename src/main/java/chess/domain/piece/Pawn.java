package chess.domain.piece;

import chess.domain.team.Team;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.UP;

public final class Pawn extends Piece {

    private boolean isFirst = true;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move) {
        if (team().equals(Team.BLACK)) {
            return DOWN.equals(move);
        }
        return UP.equals(move);
    }

    @Override
    public boolean movableByCount(final int count) {
        if (isFirst) {
            isFirst = false;
            return count <= 2;
        }
        return count <= 1;
    }
}
