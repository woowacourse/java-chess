package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class King extends Piece {
    private static final Set<Direction> directions = Set.of(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);

    public King(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "k";
        }
        return "K";
    }

    @Override
    public boolean movable(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return count <= 1;
    }
}
