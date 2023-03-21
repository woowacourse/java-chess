package chess.domain.pieces;

import static chess.domain.math.Direction.KNIGHT;

import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public final class Knight extends Piece {

    private final List<Direction> directions = List.of(KNIGHT);

    public Knight(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return directions.contains(direction);
    }

}
