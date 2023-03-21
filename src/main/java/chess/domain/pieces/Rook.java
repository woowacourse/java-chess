package chess.domain.pieces;

import static chess.domain.math.Direction.DOWN;
import static chess.domain.math.Direction.LEFT;
import static chess.domain.math.Direction.RIGHT;
import static chess.domain.math.Direction.UP;

import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public final class Rook extends Piece {

    private final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasDirection(Direction direction) {
        return directions.contains(direction);
    }
}
