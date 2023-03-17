package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Queen extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "q";
        }
        return "Q";
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
