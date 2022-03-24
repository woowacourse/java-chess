package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {
    private static final String name = "K";

    private final List<Direction> directions;

    public King() {
        super(name);
        directions = Direction.pullAllBasicDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, true);
        return directions.contains(direction);
    }

    @Override
    public String getName() {
        return name;
    }
}
