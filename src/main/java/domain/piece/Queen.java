package domain.piece;

import domain.position.Position;
import domain.position.Position.Direction;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> VALID_DIRECTIONS = Direction.directions();

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = source.direction(target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
