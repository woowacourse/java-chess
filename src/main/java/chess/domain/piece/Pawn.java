package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Pawn implements Piece {

    private static final String name = "P";

    private final List<Direction> directions;
    private final Team team;

    public Pawn(Team team) {
        this.team = team;
        directions = selectDirections(team);
    }

    private List<Direction> selectDirections(Team team) {
        if (team == Team.WHITE) {
            return Direction.pullWhitePawnDirections();
        }
        return Direction.pullBlackPawnDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, true);
        return directions.contains(direction);
    }

    @Override
    public String getName() {
        return team.convert(name);
    }
}