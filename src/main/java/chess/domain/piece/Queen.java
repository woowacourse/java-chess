package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Queen implements Piece {

    private static final String name = "Q";

    private final List<Direction> directions;
    private final Team team;

    public Queen(Team team) {
        this.team = team;
        directions = Direction.pullAllBasicDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, false);
        return directions.contains(direction);
    }

    @Override
    public String getName() {
        return team.convert(name);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, false);
    }
}