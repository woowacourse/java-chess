package domain.piece;

import domain.position.Position;
import domain.position.Position.Direction;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> VALID_DIRECTIONS = List.of(
            Direction.RIGHT_UP,
            Direction.RIGHT_DOWN,
            Direction.LEFT_UP,
            Direction.LEFT_DOWN
    );

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = source.direction(target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
