package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final String name = "Q";
    private static final List<Direction> directions = Direction.pullAllBasicDirections();

    public Queen(Team team) {
        super(name, team);
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, false);
        return directions.contains(direction);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, false);
    }
}