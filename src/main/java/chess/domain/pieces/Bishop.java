package chess.domain.pieces;

import static chess.domain.math.Direction.DOWN_LEFT;
import static chess.domain.math.Direction.DOWN_RIGHT;
import static chess.domain.math.Direction.UP_LEFT;
import static chess.domain.math.Direction.UP_RIGHT;

import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public final class Bishop extends Piece {

    private final List<Direction> directions = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    public Bishop(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return directions.contains(direction);
    }
}
