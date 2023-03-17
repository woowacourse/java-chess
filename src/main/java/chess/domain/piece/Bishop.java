package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.team.Team;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Bishop extends Piece {

    private static final Set<Direction> directions = Set.of(LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "b";
        }
        return "B";
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
