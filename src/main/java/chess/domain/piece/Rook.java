package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Rook extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "r";
        }
        return "R";
    }

    @Override
    public boolean movable(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
