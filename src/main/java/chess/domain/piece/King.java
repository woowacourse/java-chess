package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class King implements Piece {

    private static final String name = "K";

    private final List<Direction> directions;
    private final Team team;

    public King(Team team) {
        this.team = team;
        directions = Direction.pullAllBasicDirections();
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
