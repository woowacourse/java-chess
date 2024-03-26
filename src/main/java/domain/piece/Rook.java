package domain.piece;

import static domain.position.Position.Direction.DOWN;
import static domain.position.Position.Direction.LEFT;
import static domain.position.Position.Direction.RIGHT;
import static domain.position.Position.Direction.UP;

import domain.position.Position;
import domain.position.Position.Direction;
import java.util.Set;

public class Rook extends Piece {

    private static final Set<Direction> VALID_DIRECTIONS = Set.of(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = source.direction(target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
