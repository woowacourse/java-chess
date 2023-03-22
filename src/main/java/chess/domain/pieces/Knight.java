package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;

import java.util.List;

import static chess.domain.math.Direction.KNIGHT;

public class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";
    private final List<Direction> directions = List.of(KNIGHT);

    public Knight(final Team team) {
        super(team);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(KNIGHT_NAME);
            return;
        }
        this.name = new Name(KNIGHT_NAME.toLowerCase());
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return directions.contains(direction);
    }

}
