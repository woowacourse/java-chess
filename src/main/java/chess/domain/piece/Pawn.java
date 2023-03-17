package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.UP;

public final class Pawn extends Piece {

    private boolean isFirst = true;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "p";
        }
        return "P";
    }

    @Override
    public boolean movable(final Direction direction) {
        if (name().equals(name().toUpperCase())) {
            return DOWN.equals(direction);
        }
        return UP.equals(direction);
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
