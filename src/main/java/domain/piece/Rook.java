package domain.piece;

import static domain.position.Direction.DOWN;
import static domain.position.Direction.LEFT;
import static domain.position.Direction.RIGHT;
import static domain.position.Direction.UP;

import domain.position.Direction;
import domain.position.Position;
import java.util.Set;

public class Rook extends Piece {

    private static final Set<Direction> VALID_DIRECTIONS = Set.of(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.asDirection(source, target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
