package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.enums.MoveEnum;
import chess.domain.move.enums.VerticalMove;
import chess.domain.team.Team;

import static chess.domain.move.enums.VerticalMove.*;

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
    public boolean movable(final MoveEnum move) {
        if (name().equals(name().toUpperCase())) {
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
