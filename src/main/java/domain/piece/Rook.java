package domain.piece;

import domain.position.Position;
import domain.position.Position.Direction;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> VALID_DIRECTIONS = List.of(
            Direction.UP,
            Direction.DOWN,
            Direction.RIGHT,
            Direction.LEFT
    );

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = source.direction(target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
