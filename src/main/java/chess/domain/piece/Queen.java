package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {
    private static final String name = "Q";

    private final List<Direction> directions;

    public Queen() {
        super(name);
        directions = Direction.pullAllBasicDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, false);
        return directions.contains(direction);
    }

    @Override
    public String getName() {
        return name;
    }
}