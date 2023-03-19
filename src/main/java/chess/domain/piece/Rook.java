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
        String name = "r";
        if (super.team().equals(Team.WHITE)) {
            return name;
        }
        return name.toUpperCase();
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
